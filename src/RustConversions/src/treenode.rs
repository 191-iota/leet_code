use std::rc::Rc;
use std::cell::RefCell;

#[derive(Debug)]
pub struct TreeNode {
    val: i32,
    left: Option<Box<TreeNode>>,
    right: Option<Box<TreeNode>>,
}


imp TreeNode {
    fn new(val: i32) -> Rc<RefCell<Self>> {
        Rc::new(RefCell::new(TreeNode { val, left: None, right: None }))
    }
}
// impl TreeNode {
//     pub fn new(val: i32, left: Option<TreeNode>, right: Option<TreeNode>) -> Self {
//         TreeNode {
//             val,
//             left: left.map(Box::new),
//             right: right.map(Box::new),
//         }
//     }
// }
