package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.CompactDisc;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import java.util.Scanner;

public class Aims {
    public static Store store = new Store();
    public static Cart cart = new Cart();
    public static boolean available = false;
    public static Scanner input = new Scanner(System.in);

    public static void showMenu() {
        while (!available) {
            System.out.println("AIMS: ");
            System.out.println("--------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3");
            int command = input.nextInt();
            if (command == 0) {
                return;
            } else if (command == 1) {
                //display all the items in the store, and a menu as following
                store.printStore();
                storeMenu();
            } else if (command == 2) {
//                Add a media or remove a media from the store
                System.out.println("Options: ");
                System.out.println("--------------------------------");
                System.out.println("1. Add a media to the store");
                System.out.println("2. Remove a media from the store");
                System.out.println("0. Back");
                System.out.println("--------------------------------");
                System.out.println("Please choose a number: 0-1-2");
                command = input.nextInt();
                if (command == 0) {
                    return;
                } else if (command == 1) {
                    System.out.println("Enter the title of the item you want to add: ");
                }
            } else if (command == 3) {
                //display the information of the current cart and a menu below
                cart.printCart();
                cartMenu();
            }
        }
    }


    public static void storeMenu() {
        while (!available) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media’s details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4");
            int command = input.nextInt();
            if (command == 0) {
                return;
            } else if (command == 1) {
                System.out.println("Enter the title of a media: ");
                String targetTitle = input.next();
                Media foundMedia = store.searchStore(targetTitle);
                if (foundMedia != null) {
                    mediaDetailsMenu(foundMedia);
                } else {
                    System.out.println("Media not found");
                }

            } else if (command == 2) {
                //Ask the user to enter the title o the media that he/she sees on the screen
                //add the media to cart
                //check the validity of the title
                //after adding a DVD to cart, display the number of DVDs in the currents cart
                System.out.println("Enter the title of a media: ");
                String targetTitle = input.next();
                Media foundMedia = store.searchStore(targetTitle);
                if (foundMedia != null) {
                    cart.addMedia(foundMedia);
                    System.out.println("Number of items in current cart: " + cart.getNumberOfItemsOrder());
                } else {
                    System.out.println("Media not found");
                }
            } else if (command == 3) {
                //ask the same input from the user as option 2.
                //play
                System.out.println("Enter the title of a media: ");
                String targetTitle = input.next();
                Media foundMedia = store.searchStore(targetTitle);
                if (foundMedia != null) {
                    if (foundMedia instanceof CompactDisc cd) {
                        cd.play();
                    } else if (foundMedia instanceof DigitalVideoDisc dvd) {
                        dvd.play();
                    }
                } else {
                    System.out.println("Media not found");
                }
            } else if (command == 4) {
                cart.printCart();
                cartMenu();
            }
        }

    }

    public static void mediaDetailsMenu(Media a) {
        while (!available) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");
            int command = input.nextInt();
            if (command == 0) {
                return;
            } else if (command == 1) {
                cart.addMedia(a);
            } else if (command == 2) {
                if (a instanceof CompactDisc cd) {
                    cd.play();
                } else if (a instanceof DigitalVideoDisc dvd) {
                    dvd.play();
                }
            }
        }

    }

    public static void cartMenu() {
        while (!available) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");
            int command = input.nextInt();
            if (command == 0) {
                return;
            } else if (command == 1) {
                System.out.println("Options: ");
                System.out.println("1. Filter by id");
                System.out.println("2. Filter by title");
                System.out.println("0. Back");
                command = input.nextInt();
                if (command == 1) {
                    System.out.println("Enter id:");
                    command = input.nextInt();
                    cart.searchByID(command);
                } else if (command == 2) {
                    System.out.println("Enter title:");
                    String targetTitle = input.next();
                    cart.searchByTitle(targetTitle);
                }
            } else if (command == 2) {
                System.out.println("Choose whether to sort by TitleCost or CostTitle");
                System.out.println("1. Sort by TitleCost");
                System.out.println("2. Sort by CostTitle");

                command = input.nextInt();
                if (command == 1) {
                    cart.sortByTitleCost();
                } else if (command == 2) {
                    cart.sortByCostTitle();
                }
            } else if (command == 3) {
                System.out.println("Enter the title of media to remove: ");
                String targetTitle = input.next();
                Media foundMedia = cart.getMedia(targetTitle);
                cart.removeMedia(foundMedia);
            } else if (command == 4) {
                System.out.println("Enter the title of media to play: ");
                String targetTitle = input.next();
                Media foundMedia = cart.getMedia(targetTitle);
                if (foundMedia != null) {
                    if (foundMedia instanceof CompactDisc cd) {
                        cd.play();
                    } else if (foundMedia instanceof DigitalVideoDisc dvd) {
                        dvd.play();
                    }
                } else {
                    System.out.println("Media not found");
                }
            } else if (command == 5) {
                available = true;
                cart.emptyCart();
                System.out.println("An order has been created");
            }
        }
    }

    public static void main(String[] args) {
//        Cart anOrder = new Cart();
//
//        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
//        anOrder.addMedia(dvd1);
//
//        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
//        anOrder.addMedia(dvd2);
//
//        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
//        anOrder.addMedia(dvd3);

//        System.out.println(hust.soict.dsai.aims.disc.DigitalVideoDisc.getNbDigitalVideoDiscs());
//        System.out.print("Total Cost is: ");
//        System.out.println(anOrder.totalCost());
//
//        anOrder.removeDigitalVideoDisc(dvd1);
//        anOrder.removeDigitalVideoDisc(dvd2);
//        anOrder.removeDigitalVideoDisc(dvd3);
//        anOrder.removeDigitalVideoDisc(dvd1);
        showMenu();
    }
}
