
public class TreeNode<T> {
	
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;
	
	
	// main constructor
	public TreeNode(T dataNode) {
		data = dataNode;
		left = null;
		right = null;
	}
	
	// deep copy constructor
	public TreeNode(TreeNode<T> node) {
		data = node.getData();
		//copy left and right if they exist
		if (node.getRight() != null){
			right = node.getRight();
		}
		
		if (node.getLeft() != null) {
			left = node.getLeft();
		}
	}
	
	
	public T getData() {
		return data;
	}
	
	public TreeNode<T> getRight(){
		return right;
	}
	
	public TreeNode<T> getLeft(){
		return left;
	}
	
	public void setRight(TreeNode<T> newRight) {
		right = newRight;
	}
	
	public void setLeft(TreeNode<T> newLeft) {
		left = newLeft;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
}
