fn main() {
    println!("Hello, world!");
}

// does not work
pub fn longest_common_prefix(strs: Vec<String>) -> String {
    let mut common_prefix_length: usize = 0;
    let mut shortest_length = strs[0].len();

    while common_prefix_length < shortest_length {
        for i in 0..strs.len() - 1 {
            let a = strs[i].chars().nth(common_prefix_length);
            let b = strs[i + 1].chars().nth(common_prefix_length);

            if a != b {
                return strs[0].chars().take(common_prefix_length).collect();
            }

            if common_prefix_length == 0 && shortest_length > strs[i + 1].len() {
                shortest_length = strs[i + 1].len();
                if shortest_length == 0 {
                    return String::from("");
                }
            }
        }
        common_prefix_length += 1;
    }

    strs[0].chars().take(common_prefix_length).collect()
}
