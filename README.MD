# Cuts to Rolls Solver
- Uses the weighted Knapsack algorithm to solve for optimizing rolls to cuts
- Algorithm processes rolls in order from top to bottom, and assumes higher up the list, the more important
- Solution is printed out in the area shown in the blue box
- Any unused cuts are printed out in the area shown in the orange box
- Cuts and Rolls rows are inputted as FEET.INCHES
    - The process will handle inches over 12

![Cuts-to-Rolls-Solver](https://github.com/RetrobitCoder/cuts-to-rolls-solver/blob/main/Cuts-to-Rolls-Solver.png)

# Building the code
- Apache Maven at least version 3.8.7: https://maven.apache.org/download.cgi
- Java at least version 17.0.15: https://openjdk.org/projects/jdk/17/ (Recommend getting version 25 for windows)
- `mvn clean package` to build and create JAR needed for jpackage to make Windows exe

# Packaging JAR file for Windows
- Need to install https://github.com/wixtoolset
- Also need to download teh JavaFX jmods: https://gluonhq.com/products/javafx/
- JPackage should already be installed but if not, install a newer version of the JDK
- Assuming JAR file and icon file are in a folder called code, run bellow
    - You will need to udpate `--main-jar` to match the JAR name
    - Generated msi file should appear in the code folder, it does take some time

`.\openjdk-25_windows-x64_bin\jdk-25\bin\jpackage.exe --type msi --input .\code\ --name Cuts-to-Rolls-Solver --main-jar cuts-to-rolls-solver-1.0-SNAPSHOT.jar --main-class io.github.retrobitcoder.App --dest .\code\ --module-path .\openjfx-17.0.16_windows-x64_bin-jmods\javafx-jmods-17.0.16\ --add-modules java.base,javafx.controls,javafx.fxml,javafx.graphics --win-dir-chooser --win-console --icon .\code\scissors.ico --win-shortcut --win-menu --vendor io.github.retrobitcoder`
