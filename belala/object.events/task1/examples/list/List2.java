package examples.list;

public class List2 {
		private class Cell {
			Cell next;
			Object elem;

			Cell(Object elem) {
				this.elem = elem;
			}
		}

		private Cell head;

		public List2() {
		}

		public Object get(int idx) {
			Cell pos = head;
			while (idx > 0) {
				pos = pos.next;
				idx--;
			}
			return pos.elem;
		}

		public void append(Object elem) {
			if (head == null)
				head = new Cell(elem);
			else {
				Cell pos = head;
				while (pos.next != null)
					pos = pos.next;
				pos.next = new Cell(elem);
			}
		}
	
}
