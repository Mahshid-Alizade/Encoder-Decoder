
public class HashMap {

	int size = 0;

	public Object[][] map = new Object[100][2];

	// add <key,value> in HashMap
	public void put(Object key, Object value) {

		
		map[size][0] = key;
		map[size][1] = value;
		size++;
	}

	// is there key object in HashMap
	boolean containsKey(Object key) {
		for (int i = 0; map[i][0] != null && i < 100; i++) {
			if (key.equals(map[i][0])) {
				return true;
			}
		}

		return false;
	}

	// is there value object in HashMap
	boolean containsValue(Object value) {
		for (int i = 0; i < 100 && map[i][1] != null; i++) {
			if (value.equals(map[i][1])) {
				return true;
			}
		}

		return false;
	}

	// it return index of the key
	int getKeyIndex(Object key) {
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			if (key.equals(map[i][0])) {
				return i;
			}
		}
		return -1;
	}

	// get key of index i
	int getkeyWithIndex(int i) {
		int key = 0;
		for (int j = 0; j <= i && map[j][0] != null; j++) {
			key = (int) map[j][0];
		}

		return key;
	}

	// clear the HashMap
	void clear() {
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			for (int j = 0; j < 2; j++) {
				map[i][j] = null;
			}
		}
		size = 0;
	}

	// get key and return value of it
	Object get(Object key) {
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			if (key.equals(map[i][0])) {
				return map[i][1];
			}
		}
		return null;
	}

	// get value and return key
	Object getKey(Object value) {
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			if (value.equals(map[i][1])) {
				return map[i][0];
			}
		}
		return null;
	}

	// print map
	void printMap() {
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			System.out.println("num : \"" + i + "\" key : \"" + map[i][0] + "\" value :\"" + map[i][1] + "\"");
		}
	}

	// save the first code
	String getCode() {
		String heapMap = "";
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			heapMap += map[i][0] + "-" + map[i][0] + "\n";
		}
		return heapMap;
	}

	// is map contains that function
	public boolean containsFunc(Object key) {

		for (int i = 0; map[i][0] != null && i < 100; i++) {
			if (((String) map[i][0]).contains(key + "(")) {
				return true;
			}
		}
		return false;
	}

	// get the name of function and return value of it
	Object getFunc(Object key) {
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			if (((String) map[i][0]).contains(key + "(")) {
				return map[i][1];
			}
		}
		return null;
	}

	// get the name of function and return its complete key
	Object getCompleteKeyFunc(Object key) {
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			if (((String) map[i][0]).contains(key + "(")) {
				return map[i][0];
			}
		}
		return null;
	}

	// delete the last item
	void deleteLast() {
		int i;
		for (i = 0; i < 100 && map[i][0] != null; i++) {
		}

		map[i][0] = null;
		map[i][1] = null;
		size--;

	}

	// swap keys
	void swapKey(Object v1, Object k1, Object v2, Object k2) {
		for (int i = 0; i < 100 && map[i][0] != null; i++) {
			if (v1.equals(map[i][1])) {
				map[i][0] = k2;
			}

			if (v2.equals(map[i][1])) {
				map[i][0] = k1;
			}
		}
	}
	
	//return a good style for DECODE function
	String goodForDecode() {
		String letters = "";
		String numbers = "";
		for(int i = 0 ; i < this.size ; i ++)
			letters += map[i][1] + "-";
		
		for(int i = 0 ; i < this.size ; i ++)
			numbers += map[i][0] + "-";
			
		return numbers + "\n" + letters;
	}

}
