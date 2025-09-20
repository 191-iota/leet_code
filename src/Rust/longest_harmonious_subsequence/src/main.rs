fn main() {
    println!("Hello, world!");
}

// Attempt 1 - (does not work) -> this has been done for contiguous checks
// The problem mentions "subsequence", meaning that faulty entries in between can be deleted
pub fn find_lhs(nums: Vec<i32>) -> i32 {
    let mut l_ptr = 0;
    let mut r_ptr = 0;

    let mut max_seq_len = 0;

    for (i, v) in nums.iter().enumerate().skip(1) {
        if (nums[r_ptr] - v).abs() != 1 && r_ptr - l_ptr > max_seq_len {
            max_seq_len = r_ptr - l_ptr;
            r_ptr = i;
            l_ptr = i;
        }
        r_ptr += 1;
    }
    max_seq_len as i32
}
