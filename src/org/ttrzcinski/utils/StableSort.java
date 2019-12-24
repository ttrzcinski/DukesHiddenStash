package org.ttrzcinski.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StableSort {

  public static Comparator<Tuple> tupleComparator = (o1, o2) -> {
    if (o1.sum != o2.sum) {
      return o1.sum > o2.sum ? -1 : 1;
    }
    if (o1.len != o2.len) {
      return o1.len > o2.len ? -1 : 1;
    }
    if (!o1.orderedSet.equals(o2.orderedSet)) {
      return o1.orderedSet.compareToIgnoreCase(o2.orderedSet);
    }
    return 0;
  };

  public static class Tuple {

    public final int sum;
    public final String orderedSet;
    public final int len;

    public Tuple(List<Integer> chars) {
      orderedSet = chars.stream().map(String::valueOf).collect(Collectors.joining());
      sum = chars.stream().mapToInt(Integer::intValue).sum();
      len = chars.size();
    }

    @Override
    public String toString() {
      return String.format("%s %s %d", orderedSet, sum, len);
    }
  }

  public static List<Tuple> bubbleSort(List<Tuple> given) {
    int i = 0, n = given.size();
    while (i < n - 1) {
      boolean swapNeeded = false;
      for (int j = 1; j < n - i; j++) {
        if (tupleComparator.compare(given.get(j - 1), given.get(j)) < 0) {
          Tuple tmp = given.get(j - 1);
          given.set(j - 1, given.get(j));
          given.set(j, tmp);
          swapNeeded = true;
        }
      }
      if (!swapNeeded) {
        break;
      }
      i++;
    }
    return given;
  }

  public static String stableSort(String line) {
    System.out.println(line);
    String[] parts = line.split(" ");
    List<Tuple> results = new ArrayList<>();
    for (String part : parts) {
      List<Integer> chars = new ArrayList<>();
      for (char c : part.toCharArray()) {
        chars.add(Integer.valueOf(String.valueOf(c)));
      }
      Collections.sort(chars);
      results.add(new Tuple(chars));
    }

    results = bubbleSort(results);

    // Check to values
    System.out.printf("orderedSet, sum, len\n-----------%n");
    results.stream().map(Tuple::toString).forEach(System.out::println);

    return results.stream().map(x -> x.orderedSet).collect(Collectors.joining(" "));
  }
}
