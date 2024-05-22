/**
 *      ACSL 2022 Final - Problem 2 - Showtime! - Junior / Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Showtime {

    /*
     * Complete the 'displayTime' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING time as parameter.
     */
    static String displayTime (String time) {

        int[] hms = new int[3];
        hms[0] = Integer.parseInt(time.substring(0, 2));
        hms[1] = Integer.parseInt(time.substring(3, 5));
        hms[2] = Integer.parseInt(time.substring(6, 8));

        ArrayList<String> allTime = new ArrayList<>();
        generateAllTime(allTime, "", hms);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allTime.size(); i++)
        {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(allTime.get(i));
        }

        return sb.toString();
    }

    static void generateAllTime (ArrayList<String> allTime, String current, int[] hms) {

        if (current.length() == 5) {
            int[] timeHMS = getTime(current);

            if (isEqualTime(hms, timeHMS)) {
                allTime.add(current);
            }
        }
        else {
            for (int i = 0; i < colors.length(); i++)
            {
                generateAllTime(allTime, current + colors.charAt(i), hms);
            }
        }
    }

    static int[] getTime (String strTime) {

        int[] hms = new int[3];

        for (int i = 0; i < 5; i++)
        {
            char c = strTime.charAt(i);

            if (c == 'r' || c == 'y' || c == 'm' || c == 'w') {
                hms[0] += deltas[i];
            }

            if (c == 'g' || c == 'y' || c == 'c' || c == 'w') {
                hms[1] += deltas[i];
            }

            if (c == 'b' || c == 'c' || c == 'm' || c == 'w') {
                hms[2] += deltas[i];
            }
        }

        hms[1] *= 5;
        hms[2] *= 5;

        return hms;
    }

    static boolean isEqualTime (int[] hms1, int[] hms2) {
        return hms1[0] == hms2[0] && hms1[1] == hms2[1] && hms1[2] == hms2[2];
    }

    static int[] deltas = {1, 1, 2, 3, 5};
    static String colors = "bcgkmrwy";

    public static void main (String [] args) {

        String[] input = {
                "11:05:10", "11:55:45", "00:05:20", "00:20:00", "03:15:40",
                "04:15:00", "08:00:25", "05:10:40", "09:45:50", "10:20:15",
                "11:55:55", "00:00:00", "07:35:35", "01:45:05", "06:30:30"
        };

        String[] output = {
                "bwrrr cmrrr grmrr kymrr mcrrr rgmrr wbrrr ykmrr",
                "bwwyw byyww cmwyw cryww gmyww kwyww mcwyw mgyww rcyww wbwyw wkyww ybyww",
                "bcbkk bgkbk cbbkk ckkbk gbkbk kckbk",
                "gggkk gkkgk kgkgk",
                "bgcrb bkbyb brmgb bywkb ckcrb crwkb gbcrb gkgmb gmwkb grybb kbbyb kccrb kggmb kkkwb kmmgb" +
                        " krrcb kwwkb kyybb mgwkb mkmgb rbmgb rcwkb rgybb rkrcb wkwkb ybwkb ykybb",
                "grgrk krkyk kygrk rggrk rkkyk rrrgk ryykk ykgrk yrykk",
                "bbkmr bmrbr kkbmr kkkrm krmbr krrkm mbrbr rkmbr rkrkm",
                "bkckm bkwrb cgbkm cgmrb gcbkm gcmrb ggkbm ggrmb kbckm kbwrb kkgbm kkymb mrcrb rmcrb rrgmb" +
                        " wybrb ywbrb yykmb",
                "bwkww cmkww cwgmw grbww gycmw kybww mckww mwrcw rgbww rymcw wbkww wcgmw wmrcw wwybw ygcmw" +
                        " ykbww yrmcw yywbw",
                "bgmyr cgwrr ckmyr gbmyr gcwrr ggymr gkrwr kcmyr kgrwr mybyr rwbyr rykwr wrbyr wycrr ymbyr" +
                        " yrkwr ywcrr yygmr",
                "bywww crwww gmwww kwwww mgwww rcwww wkwww ybwww",
                "kkkkk",
                "bbwby bbykw ccmby ccrkw ccwcr ccygm ggmkw ggwgm kkwkw mmcby mmgkw mmwmg mmyrc rrckw rrwrc" +
                        " wwbby wwccr wwggm wwkkw wwmmg wwrrc wwwwk wwyyb yybkw yycgm yymrc yywyb",
                "bykgg crkgg cygkg gmkgg gwgkg kwkgg mgkgg rckgg wggkg wkkgg ybkgg ycgkg",
                "bybby byccr byggm bykkw bymmg byrrc bywwk byyyb crbby crccr crggm crkkw crmmg crrrc crwwk" +
                        " cryyb gmbby gmccr gmggm gmkkw gmmmg gmrrc gmwwk gmyyb kwbby kwccr kwggm kwkkw kwmmg kwrrc" +
                        " kwwwk kwyyb mgbby mgccr mgggm mgkkw mgmmg mgrrc mgwwk mgyyb rcbby rcccr rcggm rckkw rcmmg" +
                        " rcrrc rcwwk rcyyb wkbby wkccr wkggm wkkkw wkmmg wkrrc wkwwk wkyyb ybbby ybccr ybggm ybkkw" +
                        " ybmmg ybrrc ybwwk ybyyb"
        };

        for (int i = 0; i < 15; i++)
        {
            String result = displayTime(input[i]);

            if (output[i].equals(result)) {
                System.out.println("Test Case " + i + ": Passed!");
            }
            else {
                System.out.println("Test Case " + i + ": Failed!");
                System.out.println("Expected output: " + output[i]);
                System.out.println("Your output: " + result);
            }
        }
    }
}