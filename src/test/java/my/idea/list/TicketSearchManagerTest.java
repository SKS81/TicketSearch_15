package my.idea.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;

class TicketSearchManagerTest {

    private TicketSearchRepository repository = new TicketSearchRepository();
    private TicketSearchManager manager = new TicketSearchManager(repository);

    private TicketSearch Ticket1 = new TicketSearch(1, 15_000, "DME", "SIP", 200);
    private TicketSearch Ticket2 = new TicketSearch(2, 9_000, "AER", "VKO", 210);
    private TicketSearch Ticket3 = new TicketSearch(3, 13_500, "DME", "SIP", 230);
    private TicketSearch Ticket4 = new TicketSearch(4, 16_000, "SVO", "AER", 190);
    private TicketSearch Ticket5 = new TicketSearch(5, 11_000, "SVO", "SIP", 180);

    @BeforeEach
    public void addTicket() {
        repository.addTicket(Ticket1);
        repository.addTicket(Ticket2);
        repository.addTicket(Ticket3);
        repository.addTicket(Ticket4);
        repository.addTicket(Ticket5);
    }

    @Test
    public void shouldFindAllTickets() {
        TicketSearch[] expected = { Ticket1, Ticket2, Ticket3, Ticket4, Ticket5 };
        TicketSearch[] actual = repository.findAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldTicketsByPriceAndSort() {
        TicketSearch[] expected = new TicketSearch[]{Ticket2, Ticket5, Ticket3, Ticket1, Ticket4};
        TicketSearch[] actual = repository.findAllTickets();
        Arrays.sort(actual);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndSortDMEToSIP() {
        TicketSearch[] expected = new TicketSearch[]{Ticket3, Ticket1};
        TicketSearch[] actual = manager.findAllTickets("DME", "SIP");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketById() {
        repository.findTicketById(3);
        TicketSearch expected = Ticket3;
        TicketSearch actual = repository.findTicketById(3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindTicketByBadId() {
        repository.findTicketById(8);
        TicketSearch expected = null;
        TicketSearch actual = repository.findTicketById(8);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteOneTicketById() {
        repository.deleteTicketById(2);
        TicketSearch[] expected = { Ticket1, Ticket3, Ticket4, Ticket5 };
        TicketSearch[] actual = repository.findAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddOneNewTicket() {
        TicketSearch Ticket6 = new TicketSearch(6, 10_000, "SVO", "SIP", 270);
        repository.addTicket(Ticket6);
        TicketSearch[] expected = { Ticket1, Ticket2, Ticket3, Ticket4, Ticket5, Ticket6 };
        TicketSearch[] actual = repository.findAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindNot() {
        TicketSearch[] expected = new TicketSearch[]{};
        TicketSearch[] actual = manager.findAllTickets("DME", "AER");

        Assertions.assertArrayEquals(expected, actual);
    }
}