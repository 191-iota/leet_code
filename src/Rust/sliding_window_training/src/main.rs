use std::cmp;

fn main() {
    let test_vec = vec![40; 23];

    println!("{}", compute_max_window(test_vec, 3));
}

fn compute_dna_strands() {}
fn compute_max_window(arr: Vec<i32>, window_len: usize) -> i32 {
    let mut sum = 0;
    let mut res = 0;
    for (i, v) in arr.iter().enumerate() {
        sum += *v;

        if i >= window_len {
            sum -= arr[i - window_len];
        }

        if i >= window_len - 1 {
            res = cmp::max(res, sum);
        }
    }
    res
}
