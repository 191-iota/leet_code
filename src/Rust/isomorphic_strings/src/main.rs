use std::collections::HashMap;

fn main() {
    println!("Hello, world!");
}

pub fn is_isomorphic_2(s: String, t: String) -> bool {
    if s.len() != t.len() {
        return false;
    }

    let first_s_char = s.chars().next().unwrap();
    let mut cache_s = first_s_char;

    let first_t_char = t.chars().next().unwrap();
    let mut cache_t = first_t_char;

    // assumes both have eq. len()
    for (i, c) in s.chars().enumerate() {
        if cache_t != t.chars().nth(i).unwrap() && cache_s == c
            || cache_t == t.chars().nth(i).unwrap() && cache_s != c
        {
            return false;
        }

        cache_s = c;
        cache_t = t.chars().nth(i).unwrap();
    }

    true
}

// attempt 1 - does not work, checks occucrences but not isomorphism (orders need to be the same)
pub fn is_isomorphic(s: String, t: String) -> bool {
    let mut s_counts = HashMap::new();
    let mut t_counts = HashMap::new();

    for ch in s.chars() {
        *s_counts.entry(ch).or_insert(0) += 1;
    }

    for ch in t.chars() {
        *t_counts.entry(ch).or_insert(0) += 1;
    }

    let mut sv: Vec<_> = s_counts.values().cloned().collect();
    let mut tv: Vec<_> = t_counts.values().cloned().collect();

    sv.sort_unstable();
    tv.sort_unstable();

    sv == tv
}
