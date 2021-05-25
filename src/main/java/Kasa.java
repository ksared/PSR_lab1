//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;
import java.util.Map.Entry;

public class Kasa {
    private static Scanner in;

    public Kasa() {
    }

    public static void main(String[] args) throws UnknownHostException {
        HazelcastInstance hc = Hazelcast.newHazelcastInstance();
        IMap<UUID, Klient> klienci = hc.getMap("klienci");
        IMap<UUID, Sala> sale = hc.getMap("sale");
        int choice = -1;

        while(choice != 0) {
            System.out.println("Wybierz:");
            System.out.println("1 - dodaj");
            System.out.println("2 - usun");
            System.out.println("3 - aktualizuj");
            System.out.println("4. pobierz");
            System.out.println("0 - zakoncz");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0) {
                hc.shutdown();
                break;
            }

            if (choice == 1) {
                add(klienci, sale);
            } else if (choice == 2) {
                delete(klienci, sale);
            } else if (choice == 3) {
                update(klienci, sale);
            } else if (choice == 4) {
                get_hc(klienci, sale);
            }
        }

    }

    private static void get_hc(IMap<UUID, Klient> klienci, IMap<UUID, Sala> sale) {
        int choice = -1;

        while(true) {
            while(choice != 0) {
                System.out.println("Wybierz:");
                System.out.println("0 - wstecz");
                System.out.println("1 - Wyświetl klientow");
                System.out.println("2 - Znajdź i wyświetl klienta po id");
                System.out.println("3 - Znajdź i wyświetl klienta po nazwisku");
                System.out.println("4 - Wyświetl sale");
                System.out.println("5 - Znajdź i wyświetl salę po id");
                choice = Integer.parseInt(in.nextLine());
                if (choice == 0) {
                    return;
                }

                Iterator var8;
                Entry e;
                if (choice == 1) {
                    var8 = klienci.entrySet().iterator();

                    while(var8.hasNext()) {
                        e = (Entry)var8.next();
                        System.out.println(e.getKey() + " => " + e.getValue());
                    }
                } else {
                    String name;
                    if (choice == 2) {
                        System.out.println("Podaj ID: ");
                        name = in.nextLine();
                        Klient s = (Klient)klienci.get(UUID.fromString(name));
                        System.out.println(s);
                    } else {
                        Predicate namePredicate;
                        Collection w;
                        Iterator var6;
                        if (choice == 3) {
                            System.out.println("Podaj nazwisko: ");
                            name = in.nextLine();
                            namePredicate = Predicates.equal("surname", name);
                            w = klienci.values(Predicates.and(new Predicate[]{namePredicate}));
                            var6 = w.iterator();

                            while(var6.hasNext()) {
                                Klient st = (Klient)var6.next();
                                System.out.println(st);
                            }
                        } else if (choice == 4) {
                            var8 = sale.entrySet().iterator();

                            while(var8.hasNext()) {
                                e = (Entry)var8.next();
                                System.out.println(e.getKey() + " => " + e.getValue());
                            }
                        } else if (choice == 5) {
                            System.out.println("Podaj ID: ");
                            name = in.nextLine();
                            Sala s = (Sala)sale.get(UUID.fromString(name));
                            System.out.println(s);
                        }
                    }
                }
            }

            return;
        }
    }

    private static void update(IMap<UUID, Klient> klienci, IMap<UUID, Sala> sale) {
        int choice = -1;

        while(choice != 0) {
            System.out.println("Wybierz:");
            System.out.println("0 - wstecz");
            System.out.println("1 - klient");
            System.out.println("2 - sala");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0) {
                return;
            }

            String id;
            String name;
            if (choice == 1) {
                System.out.println("Podaj ID: ");
                id = in.nextLine();
                System.out.println("Podaj imie: ");
                name = in.nextLine();
                System.out.println("Podaj nazwisko: ");
                String surname = in.nextLine();
                klienci.replace(UUID.fromString(id), new Klient(name, surname));
            } else if (choice == 2) {
                System.out.println("Podaj ID: ");
                id = in.nextLine();
                System.out.println("Podaj numer sali: ");
                name = in.nextLine();
                sale.replace(UUID.fromString(id), new Sala(Integer.parseInt(name)));
            }
        }

    }

    private static void delete(IMap<UUID, Klient> klienci, IMap<UUID, Sala> sale) {
        int choice = -2;

        while(choice != 0) {
            System.out.println("Wybierz:");
            System.out.println("0 - wstecz");
            System.out.println("1 - klienci");
            System.out.println("2 - sale");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0) {
                return;
            }

            String id;
            if (choice == 1) {
                System.out.println("Podaj ID: ");
                id = in.nextLine();
                klienci.remove(UUID.fromString(id));
            } else if (choice == 2) {
                System.out.println("Podaj ID: ");
                id = in.nextLine();
                sale.remove(UUID.fromString(id));
            }
        }

    }

    private static void add(IMap<UUID, Klient> klienci, IMap<UUID, Sala> sale) {
        int choice = -1;

        while(choice != 0) {
            System.out.println("Wybierz:");
            System.out.println("0 - wstecz");
            System.out.println("1 - klienci");
            System.out.println("2 - sale");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0) {
                return;
            }

            String name;
            if (choice == 1) {
                System.out.println("Podaj imie: ");
                name = in.nextLine();
                System.out.println("Podaj nazwisko: ");
                String surname = in.nextLine();
                System.out.println("Nowy klient: " + name + " " + surname);
                klienci.put(UUID.randomUUID(), new Klient(name, surname));
            } else if (choice == 2) {
                System.out.println("Podaj numer sali: ");
                name = in.nextLine();
                System.out.println("Dodanie sali: " + name);
                sale.put(UUID.randomUUID(), new Sala(Integer.parseInt(name)));
            }
        }

    }

    static {
        in = new Scanner(System.in);
    }
}
