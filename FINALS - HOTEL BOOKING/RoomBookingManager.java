package Finals;

public class RoomBookingManager {
    private static int twinRoomACount = 0;
    private static int twinRoomBCount = 0;
    private static int twinRoomCCount = 0;
    private static int familyRoomACount = 0;
    private static int familyRoomBCount = 0;
    private static int familyRoomCCount = 0;
    private static int standardRoomACount = 0;
    private static int standardRoomBCount = 0;
    private static int standardRoomCCount = 0;
    private static int promoRoomACount = 0;
    private static int promoRoomBCount = 0;
    private static int promoRoomCCount = 0;

    private static final int MAX_ROOMS = 36;

    public static boolean bookRoom(String roomType) {
        if (getTotalBookedRooms() >= MAX_ROOMS) {
            return false; 
        }

        switch (roomType) {
            case "Twin Room A":
                if (twinRoomACount < 3) {
                    twinRoomACount++;
                    return true;
                }
                break;
            case "Twin Room B":
                if (twinRoomBCount < 3) {
                    twinRoomBCount++;
                    return true;
                }
                break;
            case "Twin Room C":
                if (twinRoomCCount < 3) {
                    twinRoomCCount++;
                    return true;
                }
                break;
            case "Family Room A":
                if (familyRoomACount < 3) {
                    familyRoomACount++;
                    return true;
                }
                break;
            case "Family Room B":
                if (familyRoomBCount < 3) {
                    familyRoomBCount++;
                    return true;
                }
                break;
            case "Family Room C":
                if (familyRoomCCount < 3) {
                    familyRoomCCount++;
                    return true;
                }
                break;
            case "Standard Room A":
                if (standardRoomACount < 3) {
                    standardRoomACount++;
                    return true;
                }
                break;
            case "Standard Room B":
                if (standardRoomBCount < 3) {
                    standardRoomBCount++;
                    return true;
                }
                break;
            case "Standard Room C":
                if (standardRoomCCount < 3) {
                    standardRoomCCount++;
                    return true;
                }
                break;
            case "Promo Room A":
                if (promoRoomACount < 3) {
                    promoRoomACount++;
                    return true;
                }
                break;
            case "Promo Room B":
                if (promoRoomBCount < 3) {
                    promoRoomBCount++;
                    return true;
                }
                break;
            case "Promo Room C":
                if (promoRoomCCount < 3) {
                    promoRoomCCount++;
                    return true;
                }
                break;
        }
        return false; 
    }

    public static boolean isRoomAvailable(String roomType) {
        switch (roomType) {
            case "Twin Room A":
                return twinRoomACount < 3;
            case "Twin Room B":
                return twinRoomBCount < 3;
            case "Twin Room C":
                return twinRoomCCount < 3;
            case "Family Room A":
                return familyRoomACount < 3;
            case "Family Room B":
                return familyRoomBCount < 3;
            case "Family Room C":
                return familyRoomCCount < 3;
            case "Standard Room A":
                return standardRoomACount < 3;
            case "Standard Room B":
                return standardRoomBCount < 3;
            case "Standard Room C":
                return standardRoomCCount < 3;
            case "Promo Room A":
                return promoRoomACount < 3;
            case "Promo Room B":
                return promoRoomBCount < 3;
            case "Promo Room C":
                return promoRoomCCount < 3;
            default:
                return false;
        }
    }

    private static int getTotalBookedRooms() {
        return twinRoomACount + twinRoomBCount + twinRoomCCount +
               familyRoomACount + familyRoomBCount + familyRoomCCount +
               standardRoomACount + standardRoomBCount + standardRoomCCount +
               promoRoomACount + promoRoomBCount + promoRoomCCount;
    }

    public static int getRoomCount(String roomType) {
        switch (roomType) {
            case "Twin Room A":
                return twinRoomACount;
            case "Twin Room B":
                return twinRoomBCount;
            case "Twin Room C":
                return twinRoomCCount;
            case "Family Room A":
                return familyRoomACount;
            case "Family Room B":
                return familyRoomBCount;
            case "Family Room C":
                return familyRoomCCount;
            case "Standard Room A":
                return standardRoomACount;
            case "Standard Room B":
                return standardRoomBCount;
            case "Standard Room C":
                return standardRoomCCount;
            case "Promo Room A":
                return promoRoomACount;
            case "Promo Room B":
                return promoRoomBCount;
            case "Promo Room C":
                return promoRoomCCount;
            default:
                return 0;
        }
    }
}
