fn main() {
    println!("Hello, world!");
}

pub fn exist(board: Vec<Vec<char>>, word: String) -> bool {
    let rows = board.len();
    let cols = board[0].len();

    let word_chars: Vec<char> = word.chars().collect();
    let mut board = board; // make mutable

    for r in 0..rows {
        for c in 0..cols {
            if board[r][c] == word_chars[0] {
                if dfs(&mut board, &word_chars, r, c, 0, rows, cols) {
                    return true;
                }
            }
        }
    }
    false
}

fn dfs(
    board: &mut Vec<Vec<char>>,
    word: &[char],
    r: usize,
    c: usize,
    idx: usize,
    rows: usize,
    cols: usize,
) -> bool {
    if r >= rows || c >= cols {
        return false;
    }

    const SENTINEL: char = '\0';
    if board[r][c] == SENTINEL {
        return false;
    }

    if board[r][c] != word[idx] {
        return false;
    }

    if idx + 1 == word.len() {
        return true;
    }

    let saved = board[r][c];
    board[r][c] = SENTINEL;

    const DIRS: [(i32, i32); 4] = [(-1, 0), (1, 0), (0, -1), (0, 1)];
    for &(dr, dc) in DIRS.iter() {
        let nr = r as i32 + dr;
        let nc = c as i32 + dc;

        // skip illegal neighbours
        if nr < 0 || nr >= rows as i32 || nc < 0 || nc >= cols as i32 {
            continue;
        }

        let (nr, nc) = (nr as usize, nc as usize);
        if dfs(board, word, nr, nc, idx + 1, rows, cols) {
            board[r][c] = saved; // restore before bubbling up
            return true;
        }
    }

    board[r][c] = saved;
    false
}
