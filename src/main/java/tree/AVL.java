package tree;

import java.util.ArrayList;

public class AVL<E extends Comparable<E>> extends BST<E> {

	protected static class AVLTreeNode<E extends Comparable<E>> extends BST.TreeNode<E> {

		protected int height = 0;

		public AVLTreeNode(E e) {
			super(e);
		}

	}

	public AVL() {
		super();
	}

	public AVL(E[] objs) {
		super(objs);
	}

	@Override
	protected TreeNode<E> createTreeNode(E e) {
		return new AVLTreeNode<>(e);
	}

	@Override
	public boolean insert(E e) {
		boolean isSuccess = super.insert(e);
		if (!isSuccess) {
			return false;
		} else {
			// 平衡二叉树
			balancePath(e);
		}
		return true;
	}

	/**
	 * 将树保持平衡
	 * 
	 * @param e
	 */
	private void balancePath(E e) {
		// 找到根结点到e结点的路径
		ArrayList<TreeNode<E>> path = path(e);
		for (int i = path.size() - 1; i >= 0; i--) {
			AVLTreeNode<E> A = (AVLTreeNode<E>) (path.get(i));
			updateHeight(A);
			AVLTreeNode<E> parentOfA = A == this.root ? null : (AVLTreeNode<E>) (path.get(i - 1));
			switch (balanceFactor(A)) {
			// 说明了左边更重
			case -2:
				if (balanceFactor((AVLTreeNode<E>) A.left) <= 0) {
					balanceLL(A, parentOfA);
				} else {
					balanceLR(A, parentOfA);
				}
				break;
			// 说明了右边更重
			case +2:
				if (balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
					balanceRR(A, parentOfA);
				} else {
					balanceRL(A, parentOfA);
				}
				break;
			}
		}
	}

	/**
	 * RL旋转
	 * 
	 * @param a
	 * @param parentOfA
	 */
	private void balanceRL(AVLTreeNode<E> a, AVLTreeNode<E> parentOfA) {
		AVLTreeNode<E> B = (AVLTreeNode<E>) a.right;
		AVLTreeNode<E> C = (AVLTreeNode<E>) B.left;
		if (a == root) {
			root = C;
		} else {
			if (parentOfA.left == a) {
				parentOfA.left = C;
			} else {
				parentOfA.right = C;
			}
		}

		a.right = C.left;
		B.left = C.right;
		C.left = a;
		C.right = B;
		updateHeight(a);
		updateHeight(B);
		updateHeight(C);
	}

	/**
	 * RR旋转
	 * 
	 * @param a
	 * @param parentOfA
	 */
	private void balanceRR(AVLTreeNode<E> a, AVLTreeNode<E> parentOfA) {
		AVLTreeNode<E> B = (AVLTreeNode<E>) a.right;
		if (a == this.root) {
			root = B;
		} else {
			if (parentOfA.left == a) {
				parentOfA.left = B;
			} else {
				parentOfA.right = B;
			}
		}

		a.right = B.left;
		B.left = a;
		updateHeight(a);
		updateHeight(B);
	}

	/**
	 * LR旋转
	 * 
	 * @param a
	 * @param parentOfA
	 */
	private void balanceLR(AVLTreeNode<E> a, AVLTreeNode<E> parentOfA) {
		AVLTreeNode<E> B = (AVLTreeNode<E>) a.left;
		AVLTreeNode<E> C = (AVLTreeNode<E>) B.right;
		if (a == root) {
			root = C;
		} else {
			if (parentOfA.left == a) {
				parentOfA.left = C;
			} else {
				parentOfA.right = C;
			}
		}

		a.left = C.right;
		B.right = C.left;
		C.left = B;
		C.right = a;
		updateHeight(a);
		updateHeight(B);
		updateHeight(C);
	}

	/**
	 * LL旋转
	 * 
	 * @param a
	 * @param parentOfA
	 */
	private void balanceLL(AVLTreeNode<E> a, AVLTreeNode<E> parentOfA) {
		AVLTreeNode<E> B = (AVLTreeNode<E>) a.left;
		if (a == this.root) {
			root = B;
		} else {
			if (parentOfA.left == a) {
				parentOfA.left = B;
			} else {
				parentOfA.right = B;
			}
		}
		a.left = B.right;
		B.right = a;
		updateHeight(a);
		updateHeight(B);
	}

	/**
	 * 更新高度
	 * 
	 * @param a
	 */
	private void updateHeight(AVLTreeNode<E> a) {
		if (a.left == null && a.right == null) {
			// 叶子结点，高度为0
			a.height = 0;
		} else if (a.left != null && a.right == null) {
			a.height = 1 + ((AVLTreeNode<E>) a.left).height;
		} else if (a.left == null && a.right != null) {
			a.height = 1 + ((AVLTreeNode<E>) a.right).height;
		} else {
			a.height = 1 + Math.max(((AVLTreeNode<E>) a.left).height, ((AVLTreeNode<E>) a.right).height);
		}
	}

	/**
	 * 平衡因子 = 右子树高度-左子树高度
	 * 
	 * @param a
	 * @return
	 */
	private int balanceFactor(AVLTreeNode<E> a) {
		if (a.right == null) {
			return -a.height;
		} else if (a.left == null) {
			return a.height;
		} else {
			return ((AVLTreeNode<E>) a.right).height - ((AVLTreeNode<E>) a.left).height;
		}
	}

	/**
	 * 找到根结点到e结点的路径
	 * 
	 * @param e
	 * @return
	 */
	private ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> path = new ArrayList<>();
		TreeNode<E> currentNode = root;
		while (currentNode != null) {
			path.add(currentNode);
			if (e.compareTo(currentNode.element) > 0) {
				currentNode = currentNode.right;
			} else if (e.compareTo(currentNode.element) < 0) {
				currentNode = currentNode.left;
			} else {
				break;
			}
		}
		return path;
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
			
			balancePath(parentNode.element);
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

			// parentOfRightMost.right == rightMost 说明了rightMost有比rightMost大的结点
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			}
			// rightMost没有右结点，rightMost是左子树上最大的结点
			else {
				parentOfRightMost.left = rightMost.left;
			}

			balancePath(parentOfRightMost.element);

		}

		size--;
		return true;
	}

}
