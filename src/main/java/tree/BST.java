package tree;

import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> extends AbstractTree<E> {

	protected TreeNode<E> root;
	protected int size;

	public static class TreeNode<E extends Comparable<E>> {
		protected E element;
		protected TreeNode<E> left;
		protected TreeNode<E> right;

		public TreeNode(E e) {
			this.element = e;
		}

		@Override
		public String toString() {
			return "TreeNode [element=" + element + ", left=" + left + ", right=" + right + "]";
		}
		
	}

	public BST() {
	}

	public BST(E[] objs) {
		for (E e : objs) {
			insert(e);
		}
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	@Override
	public boolean isContain(E e) {
		TreeNode<E> currentNode = this.root;
		while (currentNode != null) {
			// 如果该元素大于currentNode，往右找
			if (e.compareTo(currentNode.element) > 0) {
				currentNode = currentNode.right;
			}
			// 如果该元素小于于currentNode，往左找
			else if (e.compareTo(currentNode.element) < 0) {
				currentNode = currentNode.left;
			}
			// 存在
			else {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean insert(E e) {
		if (this.root == null) {
			this.root = createTreeNode(e);
			return true;
		} else {
			TreeNode<E> parentNode = null;
			TreeNode<E> currentNode = this.root;

			// 找到适合的位置
			while (currentNode != null) {
				// 如果该元素大于currentNode，往右找
				if (e.compareTo(currentNode.element) > 0) {
					parentNode = currentNode;
					currentNode = currentNode.right;
				}
				// 如果该元素小于于currentNode，往左找
				else if (e.compareTo(currentNode.element) < 0) {
					parentNode = currentNode;
					currentNode = currentNode.left;
				}
				// 已经存在
				else {
					return false;
				}
			}

			// 如果父元素小于该元素，插入在右结点
			if (e.compareTo(parentNode.element) > 0) {
				parentNode.right = createTreeNode(e);
			}
			// 如果父元素大于该元素，插入在左结点
			else {
				parentNode.left = createTreeNode(e);
			}

			//
			size++;
			return true;
		}
	}

	@Override
	public boolean delete(E e) {
		// 查找是否有该元素
		TreeNode<E> parentNode = null;
		TreeNode<E> currentNode = this.root;
		while (currentNode != null) {
			// 如果该元素大于currentNode，往右找
			if (e.compareTo(currentNode.element) > 0) {
				parentNode = currentNode;
				currentNode = currentNode.right;
			}
			// 如果该元素小于于currentNode，往左找
			else if (e.compareTo(currentNode.element) < 0) {
				parentNode = currentNode;
				currentNode = currentNode.left;
			}
			// 找到了，跳出循环
			else {
				break;
			}
		}
		// 判断当前元素是否存在
		if (currentNode == null) {
			return false;
		}

		// 1、要删除的结点的左结点为null
		if (currentNode.left == null) {
			// parentNode == null 说明该节点为根结点
			if (parentNode == null) {
				this.root = currentNode.right;
			} else {
				parentNode.left = currentNode.right;
			}
		}
		// 2、要删除的结点的左结点不为null
		else {
			// 寻找左子树上的最大值
			TreeNode<E> parentOfRightMost = currentNode;
			TreeNode<E> rightMost = currentNode.left;

			// 找出左子树上最大的结点
			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}

			// 把rightMost的值赋给currentNode
			currentNode.element = rightMost.element;

			// parentOfRightMost.right == rightMost说明了rightMost有比rightMost大的结点
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			}
			// rightMost没有右结点，rightMost是左子树上最大的结点
			else {
				parentOfRightMost = rightMost.left;
			}

		}

		size--;
		return true;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public void inorder() {
		System.out.println(root);
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new InorderIterator();
	}
	
	protected TreeNode<E> createTreeNode(E e){
		return new TreeNode<>(e);
	}

	private class InorderIterator implements Iterator<E> {

		private ArrayList<E> list = new ArrayList<>();

		private int current = 0;

		public InorderIterator() {
			inorder();
		}

		private void inorder() {
			inorder(root);
		}

		private void inorder(TreeNode<E> root) {
			// 递归写法
			if (root != null) {
				inorder(root.left);
				list.add(root.element);
				inorder(root.right);
			}
			 
			// 非递归写法
			/*Stack<TreeNode<E>> stack = new Stack<>();
			TreeNode<E> currentNode = root;
			if (root == null)
				return;
			stack.push(currentNode);
			while (!stack.isEmpty()) {
				//先找到最左边的结点
				while (currentNode.left != null) {
					currentNode = currentNode.left;
					stack.push(currentNode);
				}
				
				currentNode = stack.pop();
				
				list.add(currentNode.element);
				
				//有右结点
				if (currentNode.right != null) {
					stack.push(currentNode.right);
					currentNode = currentNode.right;
				}
			}*/

		}

		@Override
		public boolean hasNext() {
			if (current < list.size()) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			return list.get(current++);
		}

		@Override
		public void remove() {
			delete(list.get(current));
			list.clear();
			inorder();
		}

	}

	public void clear() {
		root = null;
		size = 0;
	}

}
