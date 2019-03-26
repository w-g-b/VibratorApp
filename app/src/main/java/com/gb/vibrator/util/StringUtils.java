package com.gb.vibrator.util;

/**
 * Create by wgb on 2019/3/25.
 */
public class StringUtils {
  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  public static boolean isNumOrSpace(String str) {
    return str.matches( "^[\\d\\s]+$" );
  }

  //删除多余空格
  public static String clearUnnecessarySpace(String str) {
    if (str == null || str.length() == 0) {
      return "";
    }
    return clearHeadAndTailUnnecessaryChar( str.replaceAll( "\\s{2,}", " " ) );
  }

  public static String clearHeadAndTailUnnecessaryChar(String str) {
    return clearTailUnnecessaryChar( clearHeadUnnecessaryChar( str ) );
  }

  //删除头部多余的字符
  public static String clearHeadUnnecessaryChar(String str) {
    if (str == null || str.length() == 0) {
      return "";
    }
    char tailChar = str.charAt( 0 );
    if (tailChar < '0' || tailChar > '9') {
      return str.substring( 1, str.length() - 1 );
    } else {
      return str;
    }
  }

  //删除尾部多余的字符
  public static String clearTailUnnecessaryChar(String str) {
    if (str == null || str.length() == 0) {
      return "";
    }
    char tailChar = str.charAt( str.length() - 1 );
    if (tailChar < '0' || tailChar > '9') {
      return str.substring( 0, str.length() - 1 );
    } else {
      return str;
    }
  }

  public static long[] spaceStrToLongArray(String str) {
    str = clearUnnecessarySpace( str );
    String[] strArr = str.split( "\\s" );
    long[] longArr = new long[strArr.length];
    for (int i = 0; i < strArr.length; i++) {
      longArr[i] = Long.parseLong( strArr[i] );
    }
    return longArr;
  }

  public static String formatStr(String str) {
    return clearUnnecessarySpace( str ).replaceAll( "\\s", " " );
  }

  public static void main(String[] args) {
    System.out.println( "--" + clearUnnecessarySpace( " 100 \n200 1313\t" ) + "--" );
    System.out.println( isNumOrSpace( " 100 \n200 1313\t" ) );
  }
}
