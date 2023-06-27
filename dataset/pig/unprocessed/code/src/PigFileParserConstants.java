/* Generated By:JavaCC: Do not edit this line. PigFileParserConstants.java */
package org.apache.pig.tools.parameters;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface PigFileParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int PIG = 57;
  /** RegularExpression Id. */
  int NEWLINE = 58;
  /** RegularExpression Id. */
  int SPACE = 59;
  /** RegularExpression Id. */
  int WS = 60;
  /** RegularExpression Id. */
  int COMMENT = 61;
  /** RegularExpression Id. */
  int LETTER = 62;
  /** RegularExpression Id. */
  int DIGIT = 63;
  /** RegularExpression Id. */
  int SPECIALCHAR = 64;
  /** RegularExpression Id. */
  int DOLLAR = 65;
  /** RegularExpression Id. */
  int DECLARE = 66;
  /** RegularExpression Id. */
  int PIGDEFAULT = 67;
  /** RegularExpression Id. */
  int REGISTER = 68;
  /** RegularExpression Id. */
  int IDENTIFIER = 69;
  /** RegularExpression Id. */
  int LITERAL = 70;
  /** RegularExpression Id. */
  int SHELLCMD = 71;
  /** RegularExpression Id. */
  int OTHER = 72;
  /** RegularExpression Id. */
  int NOT_OTHER_CHAR = 73;
  /** RegularExpression Id. */
  int PATH = 78;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int PIG_START = 1;
  /** Lexical state. */
  int SINGLE_LINE_COMMENT = 2;
  /** Lexical state. */
  int MULTI_LINE_COMMENT = 3;
  /** Lexical state. */
  int IN_STRING = 4;
  /** Lexical state. */
  int IN_COMMAND = 5;
  /** Lexical state. */
  int GENERATE = 6;
  /** Lexical state. */
  int SCHEMA_DEFINITION = 7;
  /** Lexical state. */
  int BAG_CONSTANT = 8;
  /** Lexical state. */
  int IN_BLOCK = 9;
  /** Lexical state. */
  int IN_DOUBLE_QUOTED_STRING = 10;
  /** Lexical state. */
  int PIG_END = 11;
  /** Lexical state. */
  int IN_REGISTER = 12;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "<token of kind 1>",
    "\"\\\'\"",
    "\"`\"",
    "<token of kind 4>",
    "<token of kind 5>",
    "\"{\"",
    "\"}\"",
    "\";\"",
    "\"--\"",
    "\"/*\"",
    "<token of kind 11>",
    "<token of kind 12>",
    "<token of kind 13>",
    "<token of kind 14>",
    "\"*/\"",
    "<token of kind 16>",
    "<token of kind 17>",
    "\"\\\\\\\'\"",
    "\"\\\'\"",
    "<token of kind 20>",
    "<token of kind 21>",
    "\"\\\\`\"",
    "\"`\"",
    "<token of kind 24>",
    "<token of kind 25>",
    "\"{\"",
    "<token of kind 27>",
    "\";\"",
    "<token of kind 29>",
    "<token of kind 30>",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "<token of kind 35>",
    "<token of kind 36>",
    "<token of kind 37>",
    "\"{\"",
    "\"}\"",
    "<token of kind 40>",
    "<token of kind 41>",
    "\"\\\"\"",
    "<token of kind 43>",
    "<token of kind 44>",
    "\"{\"",
    "<token of kind 46>",
    "\"\\\'\"",
    "\"`\"",
    "\"--\"",
    "\"/*\"",
    "<token of kind 51>",
    "<token of kind 52>",
    "\"\\\\\\\"\"",
    "\"\\\"\"",
    "<token of kind 55>",
    "<token of kind 56>",
    "\"\"",
    "<NEWLINE>",
    "<SPACE>",
    "<WS>",
    "<COMMENT>",
    "<LETTER>",
    "<DIGIT>",
    "<SPECIALCHAR>",
    "<DOLLAR>",
    "\"%declare\"",
    "\"%default\"",
    "\"register\"",
    "<IDENTIFIER>",
    "<LITERAL>",
    "<SHELLCMD>",
    "<OTHER>",
    "<NOT_OTHER_CHAR>",
    "\" \"",
    "\"\\t\"",
    "\"\\r\"",
    "\"\\n\"",
    "<PATH>",
  };

}
