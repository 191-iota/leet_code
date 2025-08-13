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

    let rev_map = ['A', 'C', 'G', 'T'];

    let mut sum: u32 = 0;
    let mut hashmap = HashMap::new();
    for (i, &s) in s_bytes.iter().enumerate() {
        let bits = map[s as usize];
        sum = (sum << 2) | bits;

        if i >= 9 {
            sum &= (1 << 20) - 1;
            *hashmap.entry(sum).or_insert(0) += 1;
        }
    }

    let mut res = Vec::new();
    let mut string = String::new();

    hashmap
        .into_iter()
        .filter(|(_, v)| *v > 1)
        .for_each(|(k, _)| {
            for i in (0..10).rev() {
                let bits = (k >> (i * 2)) & 0b11;
                string.push(rev_map[bits as usize]);
            }
            res.push(string.clone());
            string.clear();
        });
    res
}
