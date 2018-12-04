import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SleepTracker {
    private static boolean isPartOne = false;

    public static void main(String[] args) {
        List<Entry> entries = readFile();
        Map<Integer, Shift> shifts = mapShifts(entries);
        List<Shift> shiftList = getShiftsSortedByDuration(shifts);
        // These were for answer one
        Shift bestGuard = shiftList.get(0);
        getBestOverlapMinute(bestGuard, isPartOne);

        // This is for answer two
        HashMap<Shift, Overlap> shiftSleepOccuranceMap = new HashMap<>();
        for (Shift s : shiftList) {
            shiftSleepOccuranceMap.put(s, getBestOverlapMinute(s, isPartOne));
        }
        int highestOccuranceCount = 0;
        Shift bestShift = null;
        for (Map.Entry<Shift, Overlap> e : shiftSleepOccuranceMap.entrySet()) {
            if (e.getValue().getOverlapOccurance() > highestOccuranceCount) {
                highestOccuranceCount = e.getValue().getOverlapOccurance();
                bestShift = e.getKey();
            }
        }
        System.out.println("Best shift is: " );
        System.out.println("Guard id: " + bestShift.getGuardId());
        System.out.println("On minute " + shiftSleepOccuranceMap.get(bestShift).getOverlapMinute());
        System.out.println("Which occured " + shiftSleepOccuranceMap.get(bestShift).getOverlapOccurance() + " times already");
        System.out.println("Answer to riddle is : " + (bestShift.getGuardId() * shiftSleepOccuranceMap.get(bestShift).getOverlapMinute()));
    }

    private static List<Entry> readFile() {
         return readFileToList();
    }

    private static List<Entry> readFileToList() {
        List<Entry> entries = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/sleeps.txt"));
            String line;
            while((line = br.readLine()) != null) {
                entries.add(createEntryFromLine(line));
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return sortListByDate(entries);
    }

    private static Entry createEntryFromLine(String line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new Entry(LocalDateTime.parse(line.substring(1, 17), formatter), line.substring(19));
    }

    private static List<Entry> sortListByDate(List<Entry> entries) {
        return entries.stream()
                .sorted((e1, e2) -> e1.getDate().compareTo(e2.getDate()))
                .collect(Collectors.toList());
    }

    private static Map<Integer, Shift> mapShifts(List<Entry> entries) {
        Map<Integer, Shift> shifts = new HashMap<>();
        Pattern pattern = Pattern.compile("\\d+");
        Iterator<Entry> iterator = entries.iterator();
        Shift latestShift = null;
        while(iterator.hasNext()) {
            Entry e = iterator.next();
            if (e.getAction().contains("Guard")) {
                if (latestShift != null) {
                    shifts.put(latestShift.getGuardId(), latestShift);
                }
                Matcher m = pattern.matcher(e.getAction());
                if (m.find()) {
                    int guardId = Integer.parseInt(m.group(0));
                    if (shifts.containsKey(guardId)) {
                        latestShift = shifts.get(guardId);
                    } else {
                        latestShift = new Shift();
                        latestShift.setGuardId(guardId);
                    }
                }
            } else {
                if (e.getAction().contains("falls")) {
                    SleepCycle sc = new SleepCycle(e.getDate(), iterator.next().getDate());
                    latestShift.addSleepCycle(sc);
                    latestShift.addSleepDuration(sc.getSleepDuration());
                }
            }
        }
        shifts.put(latestShift.getGuardId(), latestShift);

        return shifts;
    }

    private static List<Shift> getShiftsSortedByDuration(Map<Integer, Shift> shifts) {
        List<Shift> shiftList = new ArrayList<>();
        for(Map.Entry<Integer, Shift> e : shifts.entrySet()) {
            shiftList.add(e.getValue());
        }
        return shiftList.stream().sorted(Comparator.comparing(Shift::getSleepDuration).reversed()).collect(Collectors.toList());
    }

    private static Overlap getBestOverlapMinute(Shift bestGuard, boolean isPartOne) {
        HashMap<Integer, Integer> minuteOccurances = new HashMap<>();
        for(int i = 0; i < 60; i++) {
            minuteOccurances.put(i, 0);
        }
        for(SleepCycle sc : bestGuard.getSleepCycles()) {
            for (int i = sc.getSleepStartTime().getMinute(); i < sc.getSleepEndTime().getMinute(); i++) {
                minuteOccurances.put(i, minuteOccurances.get(i) + 1);
            }
        }
        int maxOccuranceVal = Collections.max(minuteOccurances.values());
        Integer maxOccuranceMinute = 0;
        for (Map.Entry<Integer, Integer> e : minuteOccurances.entrySet()) {
            if (e.getValue() == maxOccuranceVal) {
                if (isPartOne) {
                    System.out.println("Guard id is: " + bestGuard.getGuardId());
                    System.out.println("He was asleep on the same minute " + maxOccuranceVal + " times");
                    System.out.println("Highest occurance was on minute: " + e.getKey());
                    System.out.println("Answer should be " + (bestGuard.getGuardId() * e.getKey()));
                }
                maxOccuranceMinute = e.getKey();
            }
        }

        return new Overlap(maxOccuranceMinute, maxOccuranceVal);
    }



}
