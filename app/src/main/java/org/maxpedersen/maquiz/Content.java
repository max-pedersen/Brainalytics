package org.maxpedersen.maquiz;

import java.util.ArrayList;
//Declaring the attributes of the contents class
public class Content {
    private String week;
    private String topic;
    private String content_page1;
    private String content_page2;
    private String content_page3;
    private String content_page4;

    private String youtubeLink;

//The constructor to initiate the class


    public Content(String week, String topic, String content_page1, String content_page2, String content_page3, String content_page4, String youtubeLink) {
        this.week = week;
        this.topic = topic;
        this.content_page1 = content_page1;
        this.content_page2 = content_page2;
        this.content_page3 = content_page3;
        this.content_page4 = content_page4;

        this.youtubeLink = youtubeLink;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String course) {
        this.week = course;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent_page1() {
        return content_page1;
    }

    public void setContent_page1(String content_page1) {
        this.content_page1 = content_page1;
    }

    public String getContent_page2() {
        return content_page2;
    }

    public void setContent_page2(String content_page2) {
        this.content_page2 = content_page2;
    }

    public String getContent_page3() {
        return content_page3;
    }

    public void setContent_page3(String content_page3) {
        this.content_page3 = content_page3;
    }

    public String getContent_page4() {
        return content_page4;

    }

    public void setContent_page4(String content_page4) {
        this.content_page4 = content_page4;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    //ArrayList to test the functionality of the code and layout
    public static ArrayList<Content> getContent() {
        ArrayList<Content> weeklyContent = new ArrayList<>();
        weeklyContent.add(new Content("2", "Good charts Ch. 1-4", "Five high level principles are key to visual communication. %n" +
                "1). We don't go in order: Visuals aren't read in a predictable, linear way as text is. Instead, we look first at the visual and then scan the chart for contextual clues about what is important. %n" +
                "2). We see first what stands out. Our eyes go directly to change and difference, such as unique colours, steep curves, clusters or outliers. %n" +
                "3) We see only a few things at once. The more data that's plotted on a chart, the more singular the idea it conveys. %n" +
                "4) We seek meaning and make connections. Our minds incessantly try to assign meaning to a visual and make casual connections between the elements presented. %n" +
                "", "There are two key questions to ask when deciding what kind of visual communication to undertake. %n" +
                "1) Is my information conceptual or data-driven? %n" +
                "2) Are my visuals meant to be declarative or exploratory?", "Final 3", "Final 4",  "https://www.youtube.com"));
        weeklyContent.add(new Content("3", "Good charts Ch. 5-9", "To make charts feel neat or clean, focus on design structure and hierarchy. Include four elements in all charts:\n" +
                "Title\n" +
                "Subtitle\n" +
                "Visual field (visuals, axes, labels, caption, legend)\n" +
                "Source line\n." +
                "For charts that just make sense or feel instantly understood, focus on design clarity. %n\" +\n" +
                "                \"Remove extraneous elements. Be aggressive. Take away as much as possible while maintaining meaning. Make all elements support the visual. Use them to highlight the idea, not to describe\" +\n" +
                "                \"the chart's structure. Remove ambiguity. Use conventions and metaphors. To make charts that look elegant or beautiful, focus on design simplicity. Show only what's needed. Every element should be necessary, unique, and rendered as simply as possible. \" +\n" +
                "                \"Minimize the number of colours you use. Limit eye travel (place legends and labels in close proximity to what they describe",
                "To make charts more persuasive, use three techniques. 1) Hone the main idea- Start with determining 'I need to convince them that...', then 'What am I trying to say or show? . %n" +
                        "2) Make it stand out. Use simple design techniques to reinforce your main idea. Emphasise the main idea by adding visual information that calls attention to it", "", "",  "www.youtube.com"));
        weeklyContent.add(new Content("4", "Knowledge sharing articles & videos", "h",  "", "", "", "h"));
        weeklyContent.add(new Content("5", "Predictive machines Ch. 2-6", "h", "", "", "",  "h"));
        weeklyContent.add(new Content("6", "Predictive Machines Ch. 7-11", "", "H", "", "", ""));
        weeklyContent.add(new Content("7", "Knowledge sharing articles & videos", "h", "h", "", "", ""));
        weeklyContent.add(new Content("8", "Predictive Machines Ch. 18-19", "h", "h", "", "", ""));
        weeklyContent.add(new Content("9", "Knowledge sharing articles & videos", "h", "h", "", "", ""));


        return weeklyContent;

    }
}