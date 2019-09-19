package tree;

public interface Tree<E>  extends Iterable<E> {
	/**
	 * 查找元素
	 * @param e
	 * @return
	 */
	boolean isContain(E e);
	
	/**
	 * 插入元素
	 * @param e
	 * @return
	 */
	boolean insert(E e);
	
	/**
	 * 删除元素
	 * @param e
	 * @return
	 */
	boolean delete(E e);
	
	/**
	 * 中序遍历
	 */
	void inorder();
	
	/**
	 * 后序遍历
	 */
	void postorder();
	
	/**
	 * 先序遍历
	 */
	void preorder();
	
	/**
	 * 树的大小
	 * @return
	 */
	int getSize();
	
	/**
	 * 是否为空树
	 * @return
	 */
	boolean isEmpty();
}
