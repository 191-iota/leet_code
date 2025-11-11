use std::cell::RefCell;
use std::rc::Rc;

fn main() {
    println!("Hello, world!");
}

pub fn is_valid_bst(root: Option<Rc<RefCell<TreeNode>>>) -> bool {
    dfs(root, None, None)
}

fn dfs(node: Option<Rc<RefCell<TreeNode>>>, min: Option<i32>, max: Option<i32>) -> bool {
    if let Some(n) = node {
        let n = n.borrow();

        let value = n.val;
        if let Some(min) = min {
            if value <= min {
                return false;
            }
        }

        if let Some(max) = max {
            if value >= max {
                return false;
            }
        }
        return dfs(n.left.clone(), min, Some(value)) && dfs(n.right.clone(), Some(value), max);
    }
    true
}

#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
    pub val: i32,
    pub left: Option<Rc<RefCell<TreeNode>>>,
    pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
    #[inline]
    pub fn new(val: i32) -> Self {
        TreeNode {
            val,
            left: None,
            right: None,
        }
    }
}
