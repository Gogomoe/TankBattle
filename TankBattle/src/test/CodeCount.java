package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			public Map<String, MapAndCount> map;

			public int count;

			public int fileCount;

			MapAndCount(boolean map) {
				if (map) {
					this.map = new HashMap<>();
				}
			}

			public boolean isMap() {
				return map != null;
			}
		}
		class Foreach {
			File file;

			MapAndCount root = new MapAndCount(true);

			public Foreach(File f) {
				this.file = f;
			}

			public void foreach(File f, MapAndCount mc) throws IOException {
				if (!f.exists()) {
					return;
				}
				if (f.isDirectory()) {
					MapAndCount dir = new MapAndCount(true);
					mc.map.put(f.getName(), dir);
					for (File sub : f.listFiles()) {
						foreach(sub, mc.map.get(f.getName()));
						if (dir.map.get(sub.getName()) != null) {
							dir.count += dir.map.get(sub.getName()).count;
							dir.fileCount += dir.map.get(sub.getName()).fileCount;
						}
					}
				} else if (f.isFile()) {
					if (f.getName().endsWith(".java")) {
						mc.map.put(f.getName(), new MapAndCount(false));
						mc.fileCount++;
						BufferedReader bf = new BufferedReader(new FileReader(f));
						@SuppressWarnings("unchecked")
						List<String> lines = IOUtils.readLines(bf);
						IOUtils.closeQuietly(bf);
						lines.forEach(s -> {
							if (!s.trim().isEmpty()) {
								mc.map.get(f.getName()).count++;
							}
						});
					}
				}
			}

			public void foreach() {
				try {
					foreach(file, root);
					root.count = root.map.get(file.getName()).count;
					root.fileCount = root.map.get(file.getName()).fileCount;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			public void print() {
				print(root, 0);
			}

			public void print(MapAndCount dir, int l) {
				dir.map.forEach((k, v) -> {
					char[] c = new char[l];
					Arrays.fill(c, '\t');
					System.out.println(new String(c) + k + " :" + v.count + (v.isMap() ? "  , " + v.fileCount : ""));
					if (v.isMap()) {
						print(v, l + 1);
					}
				});
			}
		}

		Foreach fo = new Foreach(new File(root));
		fo.foreach();
		switch (mode) {
		case SIMPLE:
			System.out.println("code lines : " + fo.root.count + "\tfiles : " + fo.root.fileCount);
			break;

		case DETAILED:
			fo.print();
			break;

		default:
			break;
		}
	}
}
