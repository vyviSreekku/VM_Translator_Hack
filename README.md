# VM_Translator_Hack

A Java-based Virtual Machine (VM) to Hack Assembly language translator, inspired by the Nand2Tetris course. This project parses and translates VM code into Hack assembly instructions, supporting arithmetic, logical, memory access, and branching commands.

## Project Structure

```
VM_Translator_Hack/
│
├── src/
│   ├── Main.java         # Entry point: handles file I/O and orchestrates translation
│   ├── Parser.java       # Parses VM code, removes whitespace/comments, recognizes commands
│   └── CodeWriter.java   # Translates parsed commands into Hack assembly
│
├── Files/
│   ├── vmcode.vm         # Example VM code (BasicLoop from Nand2Tetris)
│   ├── StackTest.asm     # Example translated Hack assembly (test cases)
│   └── assbly.asm        # Additional assembly example/test
│
├── .gitignore
└── README.md             # This file
```

## How It Works

- **Main.java**  
  - Sets up file paths for input VM and output ASM files.
  - Uses `Parser` to preprocess and classify VM commands.
  - Invokes `CodeWriter` to generate the corresponding Hack assembly.
  - Writes the output to a `.asm` file.

- **Parser.java**  
  - `whitespace_handler`: Removes whitespace and comments from VM code.
  - `commandRecogniser`: Classifies commands (arithmetic, push, pop, branching).

- **CodeWriter.java**  
  - Translates VM commands to Hack assembly.
  - Handles different VM segments (local, argument, this, that, constant, static, temp, pointer).
  - Supports arithmetic/logical operations, memory access, and branching.

## Example Files

- **Files/vmcode.vm**  
  Example VM program (computes sum 1+2+...+N using a loop).

- **Files/StackTest.asm**, **Files/assbly.asm**  
  Example translation outputs (Hack assembly) for use in the Nand2Tetris Hack platform.

## Usage

1. **Edit Input/Output Paths:**
   - By default, `src/Main.java` has hardcoded VM/ASM file paths.
   - Update `Inputfilename` and `Outputfilename` variables as needed.

2. **Compile:**
   ```bash
   javac src/*.java
   ```

3. **Run:**
   ```bash
   java -cp src Main
   ```
   This reads the VM file, translates it, and writes the result to the ASM file.

## Example

- **Input:**  
  See `Files/vmcode.vm` for a sample VM program.

- **Output:**  
  The translation result (Hack assembly) will be similar to `Files/StackTest.asm`.

## Dependencies

- Java (JDK 8+)

## References

- [Nand2Tetris](https://www.nand2tetris.org/)
- "The Elements of Computing Systems" by Nisan and Schocken

## License

MIT License (add your preferred license here)
