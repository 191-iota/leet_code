use std::collections::HashMap;

fn main() {
    println!("Hello, world!");
}

pub fn is_isomorphic_3(s: String, t: String) -> bool {
    if s.len() != t.len() {
        return false;
    }

    let mut s_to_t = HashMap::new();
    let mut t_mapped = HashMap::new();

    for (sc, tc) in s.chars().zip(t.chars()) {
        match (s_to_t.get(&sc), t_mapped.get(&tc)) {
            // If sc is already mapped but maps to a different tc → fail
            (Some(&mapped), _) if mapped != tc => {
                return false;
            }
            // If tc is already mapped to another sc → fail
            (None, Some(_)) => {
                return false;
            }
            // Otherwise insert both directions if not already present
            _ => {
                s_to_t.entry(sc).or_insert(tc);
                t_mapped.entry(tc).or_insert(sc);
            }
        }
    }

    true
}

pub fn is_isomorphic_2(s: String, t: String) -> bool {
    if s.len() != t.len() {
        return false;
    }

    let mut mappings = HashMap::new();

    let first_s_char = s.chars().next().unwrap();
    let mut cache_s = first_s_char;

    let first_t_char = t.chars().next().unwrap();
    let mut cache_t = first_t_char;

    for (i, c) in s.chars().enumerate() {
        let t_char = t.chars().nth(i).unwrap(); // ← minimal fix: cache nth once

        if cache_t != t_char && cache_s == c
            || cache_t == t_char && cache_s != c
            || !mappings.contains_key(&c) && mappings.values().any(|&v| v == t_char)
            || mappings.contains_key(&c) && mappings.get(&c).unwrap() != &t_char
        {
            return false;
        }

        if c != cache_s && cache_t != t_char {
            mappings.insert(cache_s, cache_t); // ← fix: insert correct pair
        }

        cache_s = c;
        cache_t = t_char;
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
