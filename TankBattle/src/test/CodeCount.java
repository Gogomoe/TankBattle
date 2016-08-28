package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class CodeCount {

	final public static String DETAILED = "Detailed";
	final public static String SIMPLE = "Simple";

	private String mode, root;

	public CodeCount(String mode, String root) {
		this.mode = mode;
		this.root = root;
	}

	public void codeLines() {

		class MapAndCount {
			public List<MapAndCount> list;

			public File file;

			public int code;// 代码数
			public int comment;// 注释数
			public int fileCount;// 文件数，如果该文件不是目录，这个值为0

			/**
			 * 表示本文件是否是一个目录
			 * 
			 * @param map
			 */
			MapAndCount(File file) {
				this.file = file;
				if (file == null || file.isDirectory()) {
					list = new ArrayList<>();
				}
			}

			boolean finish = false;

			public String getRela(MapAndCount m) {
				if (list == null) {
					return null;
				}
				if (finish) {
					return "    ";
				}
				if (list.contains(m)) {
					if (list.size() > 0 && list.get(list.size() - 1) == m) {
						finish = true;
						return "  └─";
					} else {
						return "  ├─";
					}
				}

				return "  │ ";
			}

		}
		class Foreach {
			File file;

			MapAndCount root = new MapAndCount(null);

			public Foreach(File f) {
				this.file = f;
			}

			public void foreach(File f, MapAndCount mc/* 这是File的上级目录 */) throws IOException {
				if (!f.exists()) {
					return;
				}
				if (f.isDirectory()) {
					// 把本目录添加到上级目录
					MapAndCount dir = new MapAndCount(f);
					mc.list.add(dir);

					for (File sub : f.listFiles()) {
						foreach(sub, dir);
						// 子目录遍历后，获得子目录代码行数等统计
						if (dir.list.size() > 0) {
							MapAndCount sm = dir.list.get(dir.list.size() - 1);
							if (sm.file == sub) {
								dir.code += sm.code;
								dir.comment += sm.comment;
								dir.fileCount += sm.fileCount;
							}
						}
					}
				} else if (f.isFile()) {
					if (f.getName().endsWith(".java")) {
						// 添加文件
						MapAndCount file = new MapAndCount(f);
						mc.list.add(file);
						mc.fileCount++;// 父级目录文件数增加1
						BufferedReader bf = new BufferedReader(new FileReader(f));
						@SuppressWarnings("unchecked")
						List<String> lines = IOUtils.readLines(bf);
						IOUtils.closeQuietly(bf);
						lines.forEach(s -> {
							String line = s.trim();
							if (!line.isEmpty()) {
								if (line.startsWith("//") || line.startsWith("/*") || line.startsWith("*")) {
									if (line.length() > 2) {
										file.comment++;
									}
								} else {
									file.code++;
								}
							}
						});
					}
				}
			}

			public void foreach() {
				try {
					foreach(file, root);
					root.code = root.list.get(0).code;
					root.comment += root.list.get(0).comment;
					root.file = root.list.get(0).file;
					root.fileCount = root.list.get(0).fileCount;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			public void print() {
				print(root.list.get(0));
			}

			public void print(MapAndCount dir, MapAndCount... sup) {
				dir.finish = false;
				StringBuilder pre = new StringBuilder();
				for (MapAndCount pare : sup) {
					pre.append(pare.getRela(dir));
				}
				System.out.println(pre.toString() + dir.file.getName() + " :" + dir.code + ", " + dir.comment
						+ (dir.list != null ? ", " + dir.fileCount : ""));
				if (dir.list != null) {
					MapAndCount[] sups = new MapAndCount[sup.length + 1];
					for (int i = 0; i < sup.length; i++) {
						sups[i] = sup[i];
					}
					sups[sups.length - 1] = dir;
					for (MapAndCount sub : dir.list) {
						print(sub, sups);
					}

				}

			}
		}

		Foreach fo = new Foreach(new File(root));
		fo.foreach();
		switch (mode) {
		case SIMPLE:
			System.out.println("code lines : " + fo.root.code + "\tcomments : " + fo.root.comment + "\tfiles : "
					+ fo.root.fileCount);
			break;

		case DETAILED:
			fo.print();
			break;

		default:
			break;
		}
	}
}
