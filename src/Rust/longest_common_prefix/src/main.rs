
fn main() {
    println!("Hello, world!");
}


// does not work
pub fn longest_common_prefix(strs: Vec<String>) -> String {
    let mut common_prefix_length: usize = 0;
    let mut shortest_length = strs[0].len();

    while common_prefix_length < shortest_length {
        for i in 0..strs.len() - 1 {
            if common_prefix_length == 0 && shortest_length > strs[i + 1].len() {
                shortest_length = strs[i + 1].len();

                if shortest_length == 0 {
                   return String::from("");
                }
            }
            
            if !strs[i].chars().take(common_prefix_length).eq(strs[i + 1].chars().take(common_prefix_length)) {
                return strs[i].chars().take(common_prefix_length - 1).collect();
            }

        }
        common_prefix_length += 1;
    }
    strs[0].chars().take(common_prefix_length).collect()
}
