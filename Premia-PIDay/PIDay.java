    public static String najdlhsi() {
            return "9475082805749";
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File(
                //"PI.txt"
                "PI-billion.txt"
        ))) {
            String line = sc.nextLine();
            for(int len = 2; ; len++) {
                System.out.print(len + ":");
                len:
                for(int i = 0; i < line.length()-len-1; i++) {
                    boolean ok = true;
                    for(int j = 0; j < len/2; j++)
                        if (line.charAt(i+j) != line.charAt(i+len-j-1)) {
                            ok = false;
                            break;
                        }
                    if (ok) {
                        System.out.println(len + " .. " + line.substring(i,i+len));
                        continue len;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
