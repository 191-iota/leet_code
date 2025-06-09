use std::collections::BinaryHeap;

use std::usize;

fn main() {
    println!("Hello, world!");
}

pub fn maximal_rectangle_2(matrix: Vec<Vec<char>>) -> i32 {
    let mut histo = vec![0; matrix[0].len()];
    let mut heap = BinaryHeap::new();

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
        heap.push(loop_row(&histo));
    }

    *heap.peek().unwrap()
}

fn loop_row(histo: &Vec<usize>) -> i32 {
    let mut max_area = 0;
    for i in 0..histo.len() {
        let area = area_calc_helper(i, histo[i], histo, 1);
        max_area = max_area.max(area);
    }
    max_area
}

fn area_calc_helper(idx: usize, mut smallest: usize, histo: &Vec<usize>, mut steps: usize) -> i32 {
    let mut max_area = 0;
    let mut i = idx;

    while i < histo.len() && histo[i] != 0 {
        smallest = smallest.min(histo[i]);
        max_area = max_area.max((smallest * steps) as i32);
        steps += 1;
        i += 1;
    }

    max_area
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
