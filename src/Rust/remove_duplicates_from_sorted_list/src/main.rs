use std::collections::HashSet;
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
    let mut unique_nums = HashSet::new();
    inner(&mut unique_nums, head)
}

fn inner(set: &mut HashSet<i32>, head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
    match head {
        None => None,
        Some(mut node) => {
            let next = node.next.take();
            if set.insert(node.val) {
                node.next = inner(set, next);
                Some(node)
            } else {
                inner(set, next)
            }
        }
    }
}

fn main() {
    println!("Hello, world!");
}
