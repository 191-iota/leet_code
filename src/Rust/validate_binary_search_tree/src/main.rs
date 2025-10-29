use std::cell::RefCell;
use std::rc::Rc;

fn main() {
    println!("Hello, world!");
}

pub fn is_valid_bst(root: Option<Rc<RefCell<TreeNode>>>) -> bool {

}

fn dfs(node: Option<Rc<RefCell<TreeNode>>>, min: Option<i32>, max: Option<i32>) -> bool {

    let node = unwrap_node(node);
    if node.is_none(){
        return true;
    }
    
    let value = node.val;
    if let Some(v) = min {
        if v < node.val {
            dfs(node.left, None, node.val);
            dfs(node.right, node.val, None);
        } else {
        }
    } else if let Some(v) = max {
        if v < node {
            dfs(, min, max)
        } else {
        }
    } else {
        true
    }
}

fn unwrap_node(opt: Option<Rc<RefCell<TreeNode>>>) -> Option<TreeNode> {
    opt.and_then(|rc| {
        match Rc::try_unwrap(rc) {
            Ok(refcell) => Some(refcell.into_inner()),
            Err(_) => None, // still shared, cannot take ownership
        }
    })
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
