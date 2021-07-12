package Graph;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

	static class Node {
		String name;
		List<Node> children = new ArrayList<Node>();

		public Node(String name) {
			this.name = name;
		}

		public List<String> depthFirstSearch(List<String> array) {
			array.add(this.name);
			for (int i = 0; i < children.size(); i++) {
				children.get(i).depthFirstSearch(array);
			}
			return array;
		}

		public Node addChild(String name) {
			Node child = new Node(name);
			children.add(child);
			return this;
		}
	}
}
