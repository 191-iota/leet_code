use std::collections::BinaryHeap;

use std::usize;

fn main() {
    println!("Hello, world!");
}

pub fn maximal_rectange_2(matrix: Vec<Vec<char>>) -> i32 {
    let mut first = false;
    let mut histo = vec![0, matrix[0].len()];

    for (i, row) in matrix.iter().enumerate() {
        for (j, column) in row.iter().enumerate() {
            if i == 0 {
                if matrix[i][j] == '0' {
                    histo[j] = 0;
                } else {
                    histo[j] = 1;
                }
            } else if matrix[i][j] == '0' {
                histo[j] = 0;
            } else {
                histo[j] += 1;
            }
        }
    }
}

fn gcd(mut a: i32, mut b: i32) -> i32 {
    while b != 0 {
        let t = b;
        b = a % b;
        a = t;
    }
    a.abs()
}

// How im planning to implement it:
// we will be looping through the matrix with two for loops, one horizontally and one
// vertically
//
// As soon as a one is registered another but external for loop (or recursion) will be initiated which ends upon
// an inconsistency of a rectangle, store it in a binary heap which stores the max number on top

// Attempt 1 - does not work, solve with histogram approach
pub fn maximal_rectangle(matrix: Vec<Vec<char>>) -> i32 {
    let mut heap = BinaryHeap::new();

    for (i, row) in matrix.iter().enumerate() {
        for (j, column) in row.iter().enumerate() {
            if matrix[i][j] == '1' {
                heap.push(calculate_rectangle(&matrix, i, j));
            }
        }
    }

    *heap.peek().unwrap_or(&0)
}

fn calculate_rectangle(matrix: &Vec<Vec<char>>, i: usize, j: usize) -> i32 {
    if i >= matrix.len() || j >= matrix[0].len() || matrix[i][j] != '1' {
        return 0;
    }
    // explore down and right
    let right = calculate_rectangle(matrix, i, j + 1);
    let down = calculate_rectangle(matrix, i + 1, j);

    1 + right.max(down)
}
