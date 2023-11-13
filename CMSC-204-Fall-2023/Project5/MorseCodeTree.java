import java.util.ArrayList;

public class MorseCodeTree<T> implements LinkedConverterTreeInterface<T>{
	private TreeNode<T> root;
	
	
	public MorseCodeTree() {
		buildTree();
	}

	@Override
	public TreeNode<T> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode<T> newNode) {
		root = newNode;
		
	}

	@Override
	public void insert(T code, T result) {
		addNode(root, code, result);
		
	}

	@Override
	public void addNode(TreeNode<T> root, T code, T letter) {
		// base case - at position
		TreeNode<T> add = new TreeNode<T>(letter);
		if (((String) code).length() == 1) {
			if (code.equals(".")) {
				//there is only one character, add to left
				if (root.getLeft() == null) {
					root.setLeft(add);
				}
				// more than one character
				else {
					root.getLeft().setData(letter);
				}
			}
			else {
				if (root.getRight() == null) {
					root.setRight(add);
				}
				else {
					root.getRight().setData(letter);
				}
			}
			return;
		}
		
		
		// recursive traversal
		if ((((String) code).charAt(0)) == '.'){
			// if no child, create one
			if (root.getLeft() == null) {
				root.setLeft(new TreeNode<>(null));
			}
			// call method again, setting child as new root and decrementing 'code' length
			addNode(root.getLeft(), (T)(((String)code).substring(1)), letter);
		}
		else {
			// if no child, create one
			if (root.getRight() == null) {
				root.setRight(new TreeNode<>(null));
			}
			// call method again, setting child as new root and decrementing 'code' length
			addNode(root.getRight(), (T)(((String)code).substring(1)), letter);
		}
		
	}

	@Override
	public T fetch(String code) {
		return fetchNode(root, (T)code);
	}

	@Override
	public T fetchNode(TreeNode<T> root, T code) {	
		// base case - reached last character in 'code'
		if (((String) code).length() == 1) {
			if (code.equals(".") && root.getLeft() != null) {
				return root.getLeft().getData();
			}
			else if (code.equals("-") && root.getRight() != null) {
				return root.getRight().getData();
			}
			return null;
		}
		
		// recursive travel
			// search left if a dot
		if (((String) code).charAt(0) == '.') {			
			return fetchNode(root.getLeft(), (T) ((String) code).substring(1));
		}
		
			// search right if dash
		else if  (((String) code).charAt(0) == '-'){
			return fetchNode(root.getRight(), (T) ((String) code).substring(1));
		}
		
		return null;
		
	}

	@Override
	public LinkedConverterTreeInterface<T> delete(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This operation is not supported in the MorseCodeTree");
	}

	@Override
	public LinkedConverterTreeInterface<T> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This operation is not supported in the MorseCodeTree");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildTree() {
		//building tree level by level (adding all letters)
		
		//root
		root = new TreeNode<>((T)"");

		//level 1
		insert((T)".", (T)"e");
		insert((T)"-", (T)"t");
		
		//level 2
		insert((T)"..", (T)"i");
		insert((T)".-", (T)"a");
		insert((T)"-." , (T)"n");
		insert((T)"--", (T)"m");
		
		//level 3
		insert((T)"...", (T)"s");
		insert((T)"..-", (T)"u");
		insert((T)".-.", (T)"r");
		insert((T)".--", (T)"w");
		insert((T)"-..", (T)"d");
		insert((T)"-.-", (T)"k");
		insert((T)"--.", (T)"g");
		insert((T)"---", (T)"o");
		
		//level 4
		insert((T)"....", (T)"h");
		insert((T)"...-", (T)"v");
		insert((T)"..-.", (T)"f");
		insert((T)".-..", (T)"l");
		insert((T)".--.", (T)"p");
		insert((T)".---", (T)"j");
		insert((T)"-...", (T)"b");
		insert((T)"-..-", (T)"x");
		insert((T)"-.-.", (T)"c");
		insert((T)"-.--", (T)"y");
		insert((T)"--..", (T)"z");
		insert((T)"--.-", (T)"q");
		

	}

	@Override
	public ArrayList<T> toArrayList() {
		//arraylist in LNR
		ArrayList<T> list = new ArrayList<>();
		LNRoutputTraversal(root, list);
		
		return list;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<T> root, ArrayList<T> list) {
		if (root != null) {
			//traverse left side
			LNRoutputTraversal(root.getLeft(), list);
			
			//visit node and adds to list
			if (root.getData() != null) {
				list.add(root.getData());
			}
			
			// traverse the right side
			LNRoutputTraversal(root.getRight(), list);	
		}
	}


}
