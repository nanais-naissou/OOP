package examples.list;

public class List1 {
	
	
	private class Cell {
		Cell next;
		Object elem;

		Cell(Object elem) {
			this.elem = elem;
		}

		Object get(int idx) {
			if (idx == 0)
				return elem;
			return next.get(idx - 1);
		}

		void append(Object elem) {
			if (next == null) {
				next = new Cell(elem);
			} else
				next.append(elem);
		}
	}

	Cell head;

	public List1() {
	}

	public Object get(int idx) {
		return head.get(idx);
	}

	public void append(Object elem) {
		if (head == null)
			head = new Cell(elem);
		else
			head.append(elem);
	}
}
