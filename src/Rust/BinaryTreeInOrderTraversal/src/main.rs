// use std::rc::Rc;
// use std::cell::RefCell;
mod treenode;

use treenode::TreeNode;
use std::cell::RefCell;
use std::rc::Rc;

fn main() {
    let tree = generate_symmetric_tree();
    println!("{:#?}", tree);
    print!("idk");
}

// pub fn inorder_traversal(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
// }

fn generate_symmetric_tree() -> Option<TreeNode> {
    Some(TreeNode::new(1, 
        Some(TreeNode::new(
            2, 
            Some(TreeNode::new(3, None, None)), 
            Some(TreeNode::new(4, None, None))
        )),
        Some(TreeNode::new(
            2,
            Some(TreeNode::new(4, None, None)),
            Some(TreeNode::new(3, None, None))
        )),
    ))
}

// pub fn inorder_traversal(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {

// }
