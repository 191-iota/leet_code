use std::collections::BinaryHeap;

fn main() {
    println!("Hello, world!");
}

// How im planning to implement it:
// we will be looping with through the matrix with two for loops, one horizontally and one
// vertically
//
// As soon as a one is registered another but external for loop will be initiated which ends upon
// an inconsistency of a rectangle setting the current for loops pointer to the last computed
// position

pub fn maximal_rectangle(matrix: Vec<Vec<char>>) -> i32 {
    let mut heap = BinaryHeap::new();

    for (i, row) in matrix.iter().enumerate() {
        for (j, column) in row.iter().enumerate() {
            if matrix[i][j] == '1' {
                heap.push(calculate_rectangle(matrix, i, j));
            }
        }
    }
}

fn calculate_rectangle(matrix: Vec<Vec<char>>, i: i32, j: i32) -> i32 {
    let mut biggest_state = 1;

    if i > 
}
