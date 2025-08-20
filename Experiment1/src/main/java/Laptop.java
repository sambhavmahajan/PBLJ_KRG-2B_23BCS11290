import interfaces.IBrowser;
import interfaces.ICamera;
import interfaces.IFile;
import interfaces.IInternet;

import java.util.Random;

public class Laptop implements ICamera, IBrowser, IFile, IInternet {
    private Random r = new Random();
    private String[] sentences = {
            "He dreamed of leaving his law firm to open a portable dog wash.",
            "Mothers spend months of their lives waiting on their children.",
            "She discovered van life is difficult with 2 cats and a dog.",
            "You'll see the rainbow bridge after it rains cats and dogs.",
            "As he looked out the window, he saw a clown walk by.",
            "There are few things better in life than a slice of pie.",
            "He had unknowingly taken up sleepwalking as a nighttime hobby.",
            "Choosing to do nothing is still a choice, after all.",
            "Art doesn't have to be intentional.",
            "As the asteroid hurtled toward earth, Becky was upset her dentist appointment had been canceled.",
            "He wondered why at 18 he was old enough to go to war, but not old enough to buy cigarettes.",
            "I like to leave work after my eight-hour tea-break.",
            "Two more days and all his problems would be solved.",
            "Flash photography is best used in full sunlight.",
            "The delicious aroma from the kitchen was ruined by cigarette smoke.",
            "Beach-combing replaced wine tasting as his new obsession.",
            "Edith could decide if she should paint her teeth or brush her nails.",
            "He hated that he loved what she hated about hate.",
            "Gwen had her best sleep ever on her new bed of nails.",
            "Their argument could be heard across the parking lot.",
            "He appeared to be confusingly perplexed.",
            "With the high wind warning",
            "Getting up at dawn is for the birds.",
            "Trash covered the landscape like sprinkles do a birthday cake.",
            "He kept telling himself that one day it would all somehow make sense.",
            "She borrowed the book from him many years ago and hasn't yet returned it.",
            "When I was little I had a car door slammed shut on my hand and I still remember it quite vividly.",
            "She finally understood that grief was her love with no place for it to go.",
            "I want a giraffe, but I'm a turtle eating waffles.",
            "The tour bus was packed with teenage girls heading toward their next adventure.",
            "She had convinced her kids that any mushroom found on the ground would kill them if they touched it.",
            "The miniature pet elephant became the envy of the neighborhood.",
            "He figured a few sticks of dynamite were easier than a fishing pole to catch fish.",
            "He excelled at firing people nicely.",
            "We have a lot of rain in June.",
            "The virus had powers none of us knew existed.",
            "Everyone pretends to like wheat until you mention barley.",
            "She wrote him a long letter, but he didn't read it.",
            "It's difficult to understand the lengths he'd go to remain short.",
            "David subscribes to the \"stuff your tent into the bag\" strategy over nicely folding it.",
            "The thick foliage and intertwined vines made the hike nearly impossible.",
            "The old rusted farm equipment surrounded the house predicting its demise.",
            "Stop waiting for exceptional things to just happen.",
            "She learned that water bottles are no longer just to hold liquid, but they're also status symbols.",
            "He went on a whiskey diet and immediately lost three days.",
            "Everyone was curious about the large white blimp that appeared overnight.",
            "She works two jobs to make ends meet; at least, that was her reason for not having time to join us.",
            "Love is not like pizza.",
            "Peter found road kill an excellent way to save money on dinner.",
            "Boulders lined the side of the road foretelling what could come next."
    };
    private boolean isConnectedToInternet;
    @Override
    public String searchOnDuckDuckGo(String query) {
        if(!isConnectedToInternet) {
            return "Failed to search as did not connect to the Internet";
        }
        if(query.toLowerCase().equals("deep prakash")) {
            return "Searched on Duck Duck Go " + query + "\nResult: Mr.Deep Prakash is a trainer in Chandigarh University!\n";
        }
        return "Searched on Duck Duck Go " + query + "\nResult:\n" +
                sentences[r.nextInt(sentences.length) - 1] + "\n";
    }

    @Override
    public String takePhoto() {
        return "Say cheese!!!!!!! you look nice in photo\n";
    }

    @Override
    public String openFile(String file) {
        return "Opening " + file + " but opening failed as this is a simulation\n";
    }

    @Override
    public String connectToInternet() {
        if(isConnectedToInternet) return "Already connected to internet";
        isConnectedToInternet = true;
        return "Connected to Internet\n";
    }
}
