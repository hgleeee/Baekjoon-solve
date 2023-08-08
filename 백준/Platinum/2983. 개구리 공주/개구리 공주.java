import java.io.*;
import java.util.*;

public class Main {

    static class Plant {
        int x, y;
        Plant dir1, dir2, dir3, dir4;

        public Plant(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void remove() {
            if (dir1 != null) {
                dir1.dir4 = dir4;
            }
            if (dir4 != null) {
                dir4.dir1 = dir1;
            }
            if (dir2 != null) {
                dir2.dir3 = dir3;
            }
            if (dir3 != null) {
                dir3.dir2 = dir2;
            }
        }
    }

    static int n, k;
    static List<Character> frogDir = new ArrayList<>();
    static Plant[] plantArr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        plantArr = new Plant[n];

        String input = br.readLine();
        for (int i = 0; i < k; ++i) {
            frogDir.add(input.charAt(i));
        }

        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int plantX = stoi(st.nextToken()), plantY = stoi(st.nextToken());
            Plant plant = new Plant(plantX, plantY);
            plantArr[i] = plant;
            plants.add(plant);
        }
        linkPlant(plants);
        Plant plant = jump();
        System.out.println(plant.x + " " + plant.y);
        br.close();
    }

    static void linkPlant(List<Plant> plants) {
        Collections.sort(plants, (o1, o2) -> {
            if ((o1.x - o1.y) - (o2.x - o2.y) != 0) {
                return (o1.x - o1.y) - (o2.x - o2.y);
            }
            return o1.x - o2.x;
        });
        int diff = plants.get(0).x - plants.get(0).y;
        for (int i = 1; i < n; ++i) {
            Plant plant = plants.get(i);
            if (plant.x - plant.y == diff) {
                plant.dir4 = plants.get(i-1);
                plants.get(i-1).dir1 = plant;
            } else {
                diff = plant.x - plant.y;
            }
        }

        Collections.sort(plants, (o1, o2) -> {
            if ((o1.x + o1.y) - (o2.x + o2.y) != 0) {
                return (o1.x + o1.y) - (o2.x + o2.y);
            }
            return o1.x - o2.x;
        });
        int sum = plants.get(0).x + plants.get(0).y;
        for (int i = 1; i < n; ++i) {
            Plant plant = plants.get(i);
            if (plant.x + plant.y == sum) {
                plant.dir3 = plants.get(i-1);
                plants.get(i-1).dir2 = plant;
            } else {
                sum = plant.x + plant.y;
            }
        }
    }

    static Plant jump() {
        Plant plant = plantArr[0];
        for (char charDir : frogDir) {
            switch (charDir) {
                case 'A' :
                    if (plant.dir1 != null) {
                        plant.remove();
                        plant = plant.dir1;
                    }
                    break;
                case 'B' :
                    if (plant.dir2 != null) {
                        plant.remove();
                        plant = plant.dir2;
                    }
                    break;
                case 'C' :
                    if (plant.dir3 != null) {
                        plant.remove();
                        plant = plant.dir3;
                    }
                    break;
                default:
                    if (plant.dir4 != null) {
                        plant.remove();
                        plant = plant.dir4;
                    }
                    break;
            }
        }
        return plant;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}