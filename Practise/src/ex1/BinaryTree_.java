package ex1;

class BinaryTree_ {

// Root of the Binary Tree
	Node_ root;

// This function returns overall maximum path sum in 'res'
// And returns max path sum going through root.
	int findMaxUtil(Node_ node1, Auxi res) {

// Base Case
		if (node1 == null)
			return 0;

// l and r store maximum path sum going through left and
// right child of root respectively
		int l = findMaxUtil(node1.leftValue, res);
		int r = findMaxUtil(node1.rightValue, res);

// Max path for parent call of root. This path must
// include at-most one child of root
		int max_single = Math.max(Math.max(l, r) + node1.data, node1.data);

// Max Top represents the sum when the Node under
// consideration is the root of the maxsum path and no
// ancestors of root are there in max sum path
		int max_top = Math.max(max_single, l + r + node1.data);

// Store the Maximum Result.
		res.value = Math.max(res.value, max_top);

		return max_single;
	}

	int findMaxSum() {
		return findMaxSum(root);
	}

// Returns maximum path sum in tree with given root
	int findMaxSum(Node_ node1) {

// Initialize result
// int res2 = Integer.MIN_VALUE;
		Auxi res = new Auxi();
		res.value = Integer.MIN_VALUE;

// Compute and return result
		findMaxUtil(node1, res);
		return res.value;
	}
}