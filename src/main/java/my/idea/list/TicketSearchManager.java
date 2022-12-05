package my.idea.list;

import java.util.Arrays;

public class TicketSearchManager {

    private TicketSearchRepository repository;

    public TicketSearchManager(TicketSearchRepository repository) {
        this.repository = repository;
    }

    public void addTicket(TicketSearch item) {
        repository.addTicket(item);
    }

    public TicketSearch[] findAllTickets(String from, String to) {
        TicketSearch[] result = new TicketSearch[0];
        for (TicketSearch item : repository.findAllTickets()) {
            if (item.getDeparture().equals(from) && item.getArrival().equals(to)) {
                TicketSearch[] temp = new TicketSearch[result.length + 1];
                System.arraycopy(result, 0, temp, 0, result.length);
                temp[temp.length - 1] = item;
                result = temp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}