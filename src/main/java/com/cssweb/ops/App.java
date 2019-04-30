package com.cssweb.ops;


import com.cssweb.ops.nlp.*;

public class App {

    public static void main(String[] args) {


        text();
        voice();


    }

    public static void text() {
        NLPService nlpService = new NLPService();
        if (!nlpService.init()) {
            return;
        }

        //String text = "我想要报修1号线中一路站1号闸机无法开门";
        String text = "我想要报修1号线中一路站1号闸机无法出票";
        NLPResponse nlpResponse = nlpService.sendText(text);
        if (nlpResponse == null) {
            System.out.println("语义理解失败");
            return;
        }

        if (!nlpResponse.getCode().equals("0")) {
            System.out.println("失败原因" + nlpResponse.getDesc());
            return;
        }

        for (Data data : nlpResponse.getData()) {
            System.out.println("sub " + data.getSub());
            System.out.println("text " + data.getText());

            if (data.getSub().equals("nlp")) {
                if (data.getIntent() == null) {
                    System.out.println("intent is null");
                } else {
                    System.out.println("rc = " + data.getIntent().getRc());

                    if (data.getIntent().getText() == null) {
                        System.out.println("intent is {}");
                    } else {
                        for (Semantic semantic : data.getIntent().getSemantic()) {
                            for (Slot slot : semantic.getSlots()) {
                                System.out.println("intent " + slot.getName() + ":" + slot.getValue());
                            }
                        }

                        for (MoreResults moreResults : data.getIntent().getMoreResults()) {
                            for (Semantic semantic : moreResults.getSemantic()) {
                                for (Slot slot : semantic.getSlots()) {
                                    System.out.println("moreResults " + slot.getName() + ":" + slot.getValue());
                                }
                            }
                        }
                    }//end if
                }//end if
            }//end if nlp
        }//end for
    }

    public static void voice() {
        NLPService nlpService = new NLPService();
        if (!nlpService.init()) {
            return;
        }


        NLPResponse nlpResponse = nlpService.sendAudio("test.pcm");
        if (nlpResponse == null) {
            System.out.println("语义理解失败");
            return;
        }

        if (!nlpResponse.getCode().equals("0")) {
            System.out.println("失败原因" + nlpResponse.getDesc());
            return;
        }

        for (Data data : nlpResponse.getData()) {
            System.out.println("sub " + data.getSub());
            System.out.println("text " + data.getText());

            if (data.getSub().equals("nlp")) {
                if (data.getIntent() == null) {
                    System.out.println("intent is null");
                } else {
                    System.out.println("rc = " + data.getIntent().getRc());

                    if (data.getIntent().getText() == null) {
                        System.out.println("intent is {}");
                    } else {
                        for (Semantic semantic : data.getIntent().getSemantic()) {
                            for (Slot slot : semantic.getSlots()) {
                                System.out.println("intent " + slot.getName() + ":" + slot.getValue());
                            }
                        }

                        for (MoreResults moreResults : data.getIntent().getMoreResults()) {
                            for (Semantic semantic : moreResults.getSemantic()) {
                                for (Slot slot : semantic.getSlots()) {
                                    System.out.println("moreResults " + slot.getName() + ":" + slot.getValue());
                                }
                            }
                        }
                    }//end if
                }//end if
            }//end if nlp
        }//end for
    }
}
