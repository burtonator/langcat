# Overview

Long term roadmap for the lang classifier.  

Created by burton on 3/15/2017
                    
# TODO:

- move all text to the same directory so that I can easily work with differnet 
  test/train split sizes.  

- only 160-180 languages are being tested out of 260 on wikipedia.  The other ones
  aren't used on wikipedia but we really should try to get some text for them 
  as they are still valuable.

- there is some english text in some of our models, add manual exclusions for them

- build a practical / final lang classifier with all the right settings applied

- compute a report of the LOWEST scoring language so that we can remove any 
  obviously bad code. 

- bootstrap classifier accuracy by using a basic classifier to filter out obviously 
  bad text.  Basically take a large paragram, say 250 chars, and remove the ENTIRE
  news article if < 80% of their content is int he target language. Right now 
  some of our profiles have english text in them and this way I can detect and 
  remove it as this really corrups our input data

- I need to add probabilities to the classifier and then select one with a LOWER 
  than better probability.
    
    - To compute this select the BEST language classifier and then compute how 
      well it FITS into that model.  There is a MAX distance and an actual distance.. 
      assume the MAX distance is 0% then work backwards from there.  

    - https://github.com/spinn3r/artemis/issues/2586
  
- Wikipedia has a few languages that are > 3 characters.  They seem to be custom
  ISO codes and we might have to ahve a strategy for incorporating them.
    - cantonese is one of them.  
    - none of these have many articles though
   
- create a dedicated test/training split with about 500k text to train... 500k
  is about 3500 tweets and is a good amount of text to test for accuracy.
   
- don't re-create profiles each time the VM starts .. it takes about a minute
  and that's not fun
  
- Many of the text have WILDLY varrying text lengths.. in the following example
  we are off by 10x.
 
 1.     af     96.9169032005481    50          10217     1,427,362     
 2.     am     97.3997028231798    50          1346      187,935       

    - create dedicated text/train splits for files
      
- create test/train corpora that are static and disjoint and have a lot of data.
  maybe 1-2 MB each..      

- go through and remove the excluded langs so that there are none that are excluded
    
- Review ANY languages that have a high conflict rate in the confusion matrix. 
  Especially when it conflicts with english. 

- create a test/train corpora as individual files with individual split text

- the acuraccy system should have a system of 'acceptable conflicts'

    - for example, there are about 3 languages that are VERY close to spanish.
    - then there are about 2-3 chinese dialects
    - we might have to have 'langs' in the API not lang... 
    
        // FIXME: since there are only about 1.1M unicode characters, we can
        // call it about 2M and represent this range in about 2^20 (20 bits).
        // if we limit our ngrams to 3 then we only need 60 bits to represent a
        // unique integer for this ngram.  We can do a FAST equals comparison
        // without having to compare each char. We just create a long and then
        // the first char is the first 20 bits, the second char is the next 20
        // bits, etc.   This would work well for equals or hash lookup.
    
    
    
# Wikipedia Notes
    
    - there's no easy to get any more text from wikipedia except processing the 
      HTTP log and looking at the hit count. We can get the *text* from the dumps
      but it's not in HTML and is in wiki text which is non-regular markup so
      very difficult to parse.  There are also no engagement numbers for the 
      older 
      
    -