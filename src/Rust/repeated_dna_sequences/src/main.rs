use std::collections::HashMap;

fn main() {
    println!("Hello, world!");
}

pub fn find_repeated_dna_sequences(s: String) -> Vec<String> {
    let s_bytes = s.as_bytes();
    let mut map = [0u32; 256];
    map[b'A' as usize] = 0;
    map[b'C' as usize] = 1;
    map[b'G' as usize] = 2;
    map[b'T' as usize] = 3;

    let mut sum = 0;
    let mut hashmap = HashMap::new();
    for (i, &s) in s_bytes.iter().enumerate() {
        let bits = map[s as usize];
        sum = (sum << 2) | bits;

        *hashmap.entry(sum).or_insert(0) += 1;
        if i >= 9 {
            sum &= (1 << 20) - 1;
        }
    }

    hashmap.into_iter().for_each(jkk);
}
