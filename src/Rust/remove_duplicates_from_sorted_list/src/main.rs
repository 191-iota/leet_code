use std::thread::current;

#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { next: None, val }
    }
}

pub fn delete_duplicates(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    let mut node = match head {
        Some(n) => n,
        None => return None,
    };

    let last_number = node.val;
    let mut de_duplicated: Option<Box<ListNode>> = Some(Box::new(ListNode::new(last_number)));
    de_duplicated = inner(node, Some(de_duplicated));

    de_duplicated
}

pub fn inner(head: Box<ListNode>, de_duplicated: Option<Box<ListNode>>) -> Option<Box<ListNode>> {}

fn main() {
    println!("Hello, world!");
}
