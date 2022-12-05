package my.idea.list;

public class TicketSearchRepository {

    private TicketSearch[] items = new TicketSearch[0];

    public void addTicket(TicketSearch item) {
        int length = items.length + 1;
        TicketSearch[] temp = new TicketSearch[length];
        System.arraycopy(items, 0, temp, 0, items.length);
        int lastIndex = temp.length - 1;
        temp[lastIndex] = item;
        items = temp;
    }
    public TicketSearch[] findAllTickets() {
        return items;
    }

    public void deleteTicketById(int id) {
        int length = items.length - 1;
        TicketSearch[] temp = new TicketSearch[length];
        int index = 0;
        for (TicketSearch item : items) {
            if (item.getId() != id) {
                temp[index] = item;
                index++;
            }
        }
        items = temp;
    }

    public TicketSearch findTicketById(int id) {
        for (TicketSearch item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}