
- Public code:
    
    - tests don't actually work now that I moved it into the burtonator repo
    - move the packages
    - get stats posted on per language accuracy based on classification length


- Migrate to using a TLongIntHashMap as it's a 5x memory savings.  The
  difficulty here is that I need to have this

Migra  

- It seems that we should also include unicode ranges when determining
  languages.  Certain ranges automatically indicate certain languages.

- Also padding for Arabic, Chinese, Korean, Japanese doesn't seem to make sense.


- The main problem is that I think we have less than ziph distribution with our
  current amount of text.  I think I need more realistic text and a LOT of it.
  How am I going to get it?

- I should probably call toUpperCase here.. if I don't I'll end up with aZ az AZ
  Az as valid ngrams.


- I need some decent stats on the accuracy WRT the SIZE of text it parses.  For
  example with 

    50 char strings whats the accuracy... 
    100 char strings... etc.
