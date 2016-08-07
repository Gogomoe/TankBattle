package tankbattle.core.input;

import java.util.function.Consumer;

public class KeyOperation {

	private String name;
	private String description;

	private Consumer<KeyEvent> fun;

	public KeyOperation(String name, Consumer<KeyEvent> fun) {
		super();
		this.name = name;
		this.fun = fun;
	}

	public KeyOperation(String name) {
		super();
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public KeyOperation setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getName() {
		return name;
	}

	public Consumer<KeyEvent> getFun() {
		return fun;
	}

	public KeyOperation setFun(Consumer<KeyEvent> fun) {
		this.fun = fun;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyOperation other = (KeyOperation) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
