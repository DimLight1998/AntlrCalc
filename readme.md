# Antlr Calc

## Build

```zsh
$ cd src/app
$ antlr4 LibExpr.g4 -no-listener -visitor
$ javac *.java
```

## Usage

```zsh
$ echo "a = 3\nb = 4\nc = a + 5\n2 * (1 + 3)\n(a - b) * 2\n" > calc.expr
$ java app.App calc.expr
```