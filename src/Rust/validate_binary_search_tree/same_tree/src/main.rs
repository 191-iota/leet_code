use std::cell::RefCell;
use std::rc::Rc;

fn main() {
    println!("Hello, world!");
}

pub fn is_same_tree(p: Option<Rc<RefCell<TreeNode>>>, q: Option<Rc<RefCell<TreeNode>>>) -> bool {
    check(p, q)
}

fn check(p: Option<Rc<RefCell<TreeNode>>>, q: Option<Rc<RefCell<TreeNode>>>) -> bool {
    match (p, q) {
        (None, None) => true,
        (Some(_), None) | (None, Some(_)) => false,
        (Some(pn), Some(qn)) => {
            let pn = pn.borrow();
            let qn = qn.borrow();

            if pn.val != qn.val {
                return false;
            }
            check(pn.left.clone(), qn.left.clone()) && check(pn.right.clone(), qn.right.clone())
        }
    }
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
