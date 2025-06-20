use std::mem::swap;
use std::usize;

fn main() {
    println!("Hello, world!");
}
pub fn merge_o(nums1: &mut Vec<i32>, m: i32, nums2: &mut Vec<i32>, n: i32) {
    if nums2.len() == 0 {
        return;
    }
    let mut ptr1 = 0;
    let mut ptr2 = 0;

    while ptr1 < m + n {
        if ptr2 >= n {
            break;
        }

        if nums1[ptr1 as usize] > nums2[ptr2 as usize] {
            swap(&mut nums2[ptr2 as usize], &mut nums1[ptr1 as usize]);
            ptr1 += 1;
            ptr2 += 1;
        } else if nums1[ptr1 as usize] != 0 && nums1[ptr1 as usize] <= nums2[ptr2 as usize] {
            ptr1 += 1;
        } else {
            nums1[ptr1 as usize] = nums2[ptr2 as usize];
            ptr1 += 1;
            ptr2 += 1;
        }
    }

    for i in 0..nums2.len() {
        nums1[ptr1 as usize] = nums2[i as usize];

        ptr1 += 1;
    }
}

pub fn merge(nums1: &mut Vec<i32>, m: i32, nums2: &mut Vec<i32>, n: i32) {
    let mut sorted: Vec<i32> = Vec::new();
    let mut last_m: i32 = 0;
    let mut last_n: i32 = 0;

    if m == 0 {
        for i in 0..n {
            nums1[i as usize] = nums2[i as usize];
        }
        return;
    } else if n == 0 {
        return;
    }

    while last_m + last_n < (m + n) {
        if last_m < m && last_n < n && nums1[last_m as usize] <= nums2[last_n as usize] {
            sorted.push(nums1[last_m as usize]);
            last_m += 1;
        } else if last_n < n && last_m < m && nums1[last_m as usize] >= nums2[last_n as usize] {
            sorted.push(nums2[last_n as usize]);
            last_n += 1;
        } else if last_n < n && last_m == m {
            sorted.push(nums2[last_n as usize]);
            last_n += 1;
        } else if last_n == n && last_m < m {
            sorted.push(nums1[last_m as usize]);
            last_m += 1;
        }
    }

    for i in 0..(m + n) {
        nums1[i as usize] = sorted[i as usize];
    }
}
