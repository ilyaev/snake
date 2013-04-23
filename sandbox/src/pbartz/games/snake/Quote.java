package pbartz.games.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quote {
	
	List<String> quotes;
	List<String> authors;
	
	int index = 0;
	
	public Quote() {
		quotes = new ArrayList<String>();
		authors = new ArrayList<String>();
		
		
		quotes.add("Do or do not. There is no try.");
		authors.add("Master Yoda");
		
		quotes.add("Shit happens.");
		authors.add("Forrest Gump");
		
		quotes.add("If you long to look at the moon,\n you can become an idiot");
		authors.add("Michalich");
		
		quotes.add("It is unbecoming for young men to\n utter maxims.");
		authors.add("Aristotle");

		quotes.add("Man is fully responsible for his\n nature and his choices.");
		authors.add("Sartre");

		quotes.add("Rhetoric is the art of ruling\n the minds of men.");
		authors.add("Plato");

		quotes.add("What worries you, masters you.");
		authors.add("John Locke");

		quotes.add("From each according to his abilities, \nto each according to his needs.");
		authors.add("Karl Marx");

		quotes.add("Any man can make mistakes, \nbut only an idiot persists in his error.");
		authors.add("Cicero");

		quotes.add("Tardiness often robs us opportunity, \nand the dispatch of our forces.");
		authors.add("Machiavelli");

		quotes.add("Some laws of state aimed at curbing crime\n are even more criminal.");
		authors.add("Friedrich Engels");

		quotes.add("In war there is no prize for runner-up.");
		authors.add("Seneca");

		quotes.add("Je pense, donc je suis.");
		authors.add("Descartes");

		quotes.add("Talking much about oneself can also \nbe a means to conceal oneself.");
		authors.add("Nietzsche");

		quotes.add("Your descendants shall gather\n your fruits.");
		authors.add("Virgil");

		quotes.add("Beauty in things exists in the mind\n which contemplates them.");
		authors.add("David Hume");

		quotes.add("Plant and your spouse plants with you;\n weed and you weed alone.");
		authors.add("Rousseau");

		quotes.add("Orators are most vehement when\n their cause is weak.");
		authors.add("Cicero");

		quotes.add("Reject your sense of injury\n and the injury itself disappears.");
		authors.add("Marcus Aurelius");

		quotes.add("Never go to excess,\n but let moderation be your guide.");
		authors.add("Cicero");

		quotes.add("One change always leaves the way\n open for the establishment of others.");
		authors.add("Machiavelli");

		quotes.add("It is a clear gain to sacrifice pleasure\n in order to avoid pain.");
		authors.add("Schopenhauer");

		quotes.add("Plant and your spouse plants with you;\n weed and you weed alone.");
		authors.add("Rousseau");

		quotes.add("Study the past,\n if you would divine the future.");
		authors.add("Confucius");

		quotes.add("States are as the men, \nthey grow out of human characters.");
		authors.add("Plato");

		quotes.add("To study the abnormal is the best way\n of understanding the normal.");
		authors.add("William James");

		quotes.add("Any man can make mistakes,\nbut only an idiot persists in his error.");
		authors.add("Cicero");

		quotes.add("In all things of nature\n there is something of the marvelous.");
		authors.add("Aristotle");

		quotes.add("We must act out passion before we can feel it.");
		authors.add("Sartre");

		quotes.add("We do not know what is really good\n or bad fortune.");
		authors.add("Rousseau");

		quotes.add("An education obtained with money\n is worse than no education at all");
		authors.add("Socrates");

		quotes.add("Nothing is to be preferred before justice.");
		authors.add("Socrates");

		quotes.add("Whereof one cannot speak, \nthereof one must be silent.");
		authors.add("Wittgenstein");

		quotes.add("I don’t trust liberals, \nI trust conservatives.");
		authors.add("Seneca");

		quotes.add("Before beginning, plan carefully.");
		authors.add("Cicero");

		quotes.add("I shall assume that your silence\n gives consent.");
		authors.add("Plato");

		quotes.add("A sword never kills anybody; \nit is a tool in the killer’s hand.");
		authors.add("Seneca");

		quotes.add("The soul becomes dyed with the\n color of its thoughts.");
		authors.add("Marcus Aurelius");

		quotes.add("We can’t all do everything.");
		authors.add("Virgil");

		quotes.add("The object of the superior man is truth.");
		authors.add("Confucius");

		quotes.add("Je pense, donc je suis.");
		authors.add("Descartes");

		quotes.add("Life begins on the other side of despair.");
		authors.add("Sartre");

		quotes.add("A man of courage is also full of faith.");
		authors.add("Cicero");

		quotes.add("You must live for another \nif you wish to live for yourself.");
		authors.add("Seneca");

		quotes.add("None but himself can be his parallel.");
		authors.add("Virgil");


		quotes.add("The universe is transformation; \nour life is what our thoughts make it.");
		authors.add("Marcus Aurelius");

		quotes.add("Whatever deceives men seems to\n produce a magical enchantment.");
		authors.add("Plato");

		quotes.add("It is the quality rather than\n the quantity that matters.");
		authors.add("Seneca");

		quotes.add("De omnibus dubitandum");
		authors.add("Descartes");

		quotes.add("No man’s knowledge here can go\n beyond his experience.");
		authors.add("John Locke");

		quotes.add("Brave men rejoice in adversity,\n just as brave soldiers triumph in war.");
		authors.add("Seneca");

		quotes.add("Courage is knowing what not to fear.");
		authors.add("Plato");

		quotes.add("To free a person from error is to give,\n and not to take away.");
		authors.add("Schopenhauer");

		quotes.add("The discipline of desire is\n the background of character.");
		authors.add("John Locke");

		quotes.add("Je pense, donc je suis.");
		authors.add("Descartes");

		quotes.add("The higher we are placed,\n the more humbly we should walk.");
		authors.add("Cicero");

		quotes.add("There is nothing good or evil save in the will.");
		authors.add("Epictetus");

		quotes.add("The best revenge is to be unlike him\n who performed the injury.");
		authors.add("Marcus Aurelius");

		quotes.add("Call no man unhappy until he is married.");
		authors.add("Socrates");

		quotes.add("When men speak ill of thee, \nlive so as nobody may believe them.");
		authors.add("Plato");

		quotes.add("One must steer, not talk.");
		authors.add("Seneca");

		quotes.add("The object of the superior man is truth.");
		authors.add("Confucius");

		quotes.add("Curiosity is the lust of the mind.");
		authors.add("Thomas Hobbes");

		quotes.add("What can I know? What ought I to do? \nWhat can I hope?");
		authors.add("Kant");

		quotes.add("To suffer the penalty of too much haste,\n which is too little speed.");
		authors.add("Plato");

		quotes.add("Thinking: the talking of the soul with itself.");
		authors.add("Plato");

		quotes.add("No obligation to do the impossible is binding.");
		authors.add("Cicero");

		quotes.add("No great genius has ever existed\n without some touch of madness.");
		authors.add("Aristotle");

		quotes.add("Youth is easily deceived \nbecause it is quick to hope.");
		authors.add("Aristotle");

		quotes.add("A friend is, as it were, a second self.");
		authors.add("Cicero");

		quotes.add("Patience is the companion of wisdom.");
		authors.add("Saint Augustine");

		quotes.add("Age carries all things away,\n even the mind.");
		authors.add("Virgil");

		quotes.add("Rightly defined philosophy\n is simply the love of wisdom.");
		authors.add("Cicero");

		quotes.add("Sweet is the memory of past troubles.");
		authors.add("Cicero");

		quotes.add("If you have a garden and a library,\n you have everything you need.");
		authors.add("Cicero");

		quotes.add("To study the abnormal\n is the best way of understanding the normal.");
		authors.add("William James");

		quotes.add("See one promontory, one mountain, \none sea, one river and see all.");
		authors.add("Socrates");

		quotes.add("They succeed, because they think they can.");
		authors.add("Virgil");

		quotes.add("Temperance is a mean with regard to pleasures.");
		authors.add("Aristotle");

		quotes.add("Being is. Being is in-itself.\n Being is what it is.");
		authors.add("Sartre");

		quotes.add("I fear the Greeks, \neven when they bring gifts.");
		authors.add("Virgil");

		quotes.add("Men often act knowingly against their interest.");
		authors.add("David Hume");

		quotes.add("Necessity… the mother of invention.");
		authors.add("Plato");

		quotes.add("He who does not prevent a crime when\n he can, encourages it.");
		authors.add("Seneca");

		quotes.add("Trust one who has tried.");
		authors.add("Virgil");

		quotes.add("There is no great genius without some touch of madness.");
		authors.add("Seneca");

		quotes.add("They must often change, \nwho would be constant in happiness or wisdom.");
		authors.add("Confucius");

		quotes.add("Hug the shore; \nlet others try the deep.");
		authors.add("Virgil");

		quotes.add("Liberty is the right to do\n what the law permits.");
		authors.add("Montesquieu");

		quotes.add("It is double pleasure to deceive the deceiver.");
		authors.add("Machiavelli");

		quotes.add("Philosophy is the highest music.");
		authors.add("Plato");

		quotes.add("The essence of genius is to know what to overlook.");
		authors.add("William James");

		quotes.add("Crime when it succeeds is called virtue.");
		authors.add("Seneca");

		quotes.add("When I think over what I have said, \nI envy dumb people.");
		authors.add("Seneca");

		quotes.add("A new word is like a fresh seed\n sown on the ground of the discussion.");
		authors.add("Wittgenstein");

		quotes.add("Love, and do what you like.");
		authors.add("Saint Augustine");

		quotes.add("The superior man is modest in his speech, \nbut exceeds in his actions.");
		authors.add("Confucius");

		quotes.add("Honor means that a man is not exceptional;\n fame, that he is.");
		authors.add("Schopenhauer");

		quotes.add("Friendship is essentially a partnership.");
		authors.add("Aristotle");

		quotes.add("Never go to excess,\n but let moderation be your guide.");
		authors.add("Cicero");

		quotes.add("To change ones life: Start immediately.\n Do it flamboyantly.");
		authors.add("William James");

		quotes.add("Belief creates the actual fact.");
		authors.add("William James");

		quotes.add("Wherever the fates lead us let us follow.");
		authors.add("Virgil");

		quotes.add("I criticize by creation – \nnot by finding fault.");
		authors.add("Cicero");

		quotes.add("You will be as much value to others \nas you have been to yourself.");
		authors.add("Cicero");

		quotes.add("If you wish to be a writer, write.");
		authors.add("Epictetus");

		quotes.add("The misfortune of the wise is better than\n the prosperity of the fool.");
		authors.add("Epicurus");

		quotes.add("Landlords, like all other men,\n love to reap where they never sowed.");
		authors.add("Karl Marx");

		quotes.add("Nature abhors annihilation.");
		authors.add("Cicero");

		quotes.add("Only the wisest and stupidest of men \nnever change.");
		authors.add("Confucius");

		quotes.add("Do not laugh much or often\n or unrestrainedly.");
		authors.add("Epictetus");

		quotes.add("The sinews of war are infinite money.");
		authors.add("Cicero");

		quotes.add("The law always limits every power\n it gives.");
		authors.add("David Hume");

		quotes.add("Once made equal to man, \nwoman becomes his superior.");
		authors.add("Socrates");

		quotes.add("Be content to seem what you really are.");
		authors.add("Marcus Aurelius");

		quotes.add("How many are the things I can do\n without!");
		authors.add("Socrates");

		quotes.add("Peace is a natural effect of trade.");
		authors.add("Montesquieu");

		quotes.add("The worst form of inequality\n is to try to make unequal things equal.");
		authors.add("Aristotle");


		quotes.add("For men are not equal: thus speaks justice.");
		authors.add("Nietzsche");

		quotes.add("Your life is what your thoughts make it.");
		authors.add("Marcus Aurelius");

		quotes.add("The gods too are fond of a joke.");
		authors.add("Aristotle");

		quotes.add("How many famous and high-spirited\n heroes have lived a day too long?");
		authors.add("Rousseau");
		
		//continue

		quotes.add("No untroubled day has ever\n dawned for me.");
		authors.add("Seneca");

		quotes.add("A punishment to some, to some a gift,\n and to many a favor.");
		authors.add("Seneca");

		quotes.add("Courage is a mean with regard to fear\n and confidence.");
		authors.add("Aristotle");

		quotes.add("Our life is what our thoughts make it.");
		authors.add("Marcus Aurelius");

		quotes.add("They can because they think they can.");
		authors.add("Virgil");

		quotes.add("If particulars are to have meaning,\n there must be universals.");
		authors.add("Plato");

		quotes.add("When you have no basis for an argument,\n abuse the plaintiff.");
		authors.add("Cicero");

		quotes.add("Hatred is inveterate anger.");
		authors.add("Cicero");

		quotes.add("Punishment is justice for the unjust.");
		authors.add("Saint Augustine");

		quotes.add("Justice is the set and constant purpose\n which gives every man his due.");
		authors.add("Cicero");

		quotes.add("Anger is like those ruins which smash \nthemselves on what they fall.");
		authors.add("Seneca");

		quotes.add("Everything in the world is purchased\n by labor.");
		authors.add("David Hume");

		quotes.add("Control thy passions lest they \ntake vengence on thee.");
		authors.add("Epictetus");

		quotes.add("For greed all nature is too little.");
		authors.add("Seneca");

		quotes.add("Every man has a right to risk\n his own life for the preservation of it.");
		authors.add("Rousseau");

		quotes.add("Misfortune shows those who are not\n really friends.");
		authors.add("Aristotle");

		quotes.add("It does not matter how slowly you go\n as long as you do not stop.");
		authors.add("Confucius");

		quotes.add("Homer has taught all other poets\n the art of telling lies skillfully.");
		authors.add("Aristotle");

		quotes.add("Opinion is the medium between knowledge\n and ignorance.");
		authors.add("Plato");

		quotes.add("O Lord, help me to be pure, but not yet.");
		authors.add("Saint Augustine");

		quotes.add("O accursed hunger of gold, \nto what dost thou not compel human hearts!");
		authors.add("Virgil");

		quotes.add("Fate will find a way.");
		authors.add("Virgil");

		quotes.add("Not cohabitation but consensus\n constitutes marriage.");
		authors.add("Cicero");

		quotes.add("Act as if what you do makes a difference.\n It does.");
		authors.add("William James");

		quotes.add("Before all else, be armed.");
		authors.add("Machiavelli");

		quotes.add("A wise man proportions his belief\n to the evidence.");
		authors.add("David Hume");

		quotes.add("The strength of a nation derives from\n the integrity of the home.");
		authors.add("Confucius");

		quotes.add("Peace is liberty in tranquillity.");
		authors.add("Cicero");

		quotes.add("There are remarks that sow and remarks\n that reap.");
		authors.add("Wittgenstein");

		quotes.add("We should not moor a ship with one anchor,\n or our life with one hope.");
		authors.add("Epictetus");

		quotes.add("The human body is essentially something other\n than an animal organism.");
		authors.add("Martin Heidegger");

		quotes.add("The greatest pleasures are only narrowly\n separated from disgust.");
		authors.add("Cicero");

		quotes.add("The only excuse for war is that we may\n live in peace unharmed.");
		authors.add("Cicero");

		quotes.add("You cannot open a book without\n learning something.");
		authors.add("Confucius");

		quotes.add("Crito, I owe a cock to Asclepius;\n will you remember to pay the debt?");
		authors.add("Socrates");

		quotes.add("The medicine increases the disease.");
		authors.add("Virgil");

		quotes.add("The pursuit, even of the best things,\n ought to be calm and tranquil.");
		authors.add("Cicero");

		quotes.add("To the wise, life is a problem;\n to the fool, a solution.");
		authors.add("Marcus Aurelius");

		quotes.add("Attention to health is life greatest\n hindrance.");
		authors.add("Plato");

		quotes.add("To become truly great, one has to\n stand with people, not above them.");
		authors.add("Montesquieu");

		quotes.add("No man is free who is not master of himself.");
		authors.add("Epictetus");

		quotes.add("Revolutions are the locomotives of history.");
		authors.add("Karl Marx");

		quotes.add("Grant what thou commandest and then\n command what thou wilt.");
		authors.add("Saint Augustine");

		quotes.add("Whenever the speech is corrupted\n so is the mind.");
		authors.add("Seneca");

		quotes.add("Know thyself.");
		authors.add("Socrates");

		quotes.add("Confine yourself to the present.");
		authors.add("Marcus Aurelius");

		quotes.add("Good can exist without evil,\n whereas evil cannot exist without good.");
		authors.add("Saint Thomas Aquinas");

		quotes.add("Sometimes even to live is an\n act of courage.");
		authors.add("Seneca");

		quotes.add("Truth is what works.");
		authors.add("William James");

		quotes.add("What then is freedom?\n The power to live as one wishes.");
		authors.add("Cicero");

		quotes.add("Oh Lord, give me chastity,\n but do not give it yet.");
		authors.add("Saint Augustine");

		quotes.add("After your death you will be\n what you were before your birth.");
		authors.add("Schopenhauer");

		quotes.add("Do every act of your life\n as if it were your last.");
		authors.add("Marcus Aurelius");

		quotes.add("Man is born free,\n and everywhere he is in shackles.");
		authors.add("Rousseau");

		quotes.add("All wealth is the product of labor.");
		authors.add("John Locke");

		quotes.add("A sword never kills anybody;\n it is a tool in the killer’s hand.");
		authors.add("Seneca");

		quotes.add("Nothing can have value without\n being an object of utility.");
		authors.add("Karl Marx");

		quotes.add("Time itself comes in drops.");
		authors.add("William James");

		quotes.add("Character is the result of a system\n of stereotyped principals.");
		authors.add("David Hume");

		quotes.add("You are a little soul carrying\n around a corpse.");
		authors.add("Epictetus");

		quotes.add("Let not our proposal be disregarded\n on the score of our youth.");
		authors.add("Virgil");

		quotes.add("Fury itself supplies arms.");
		authors.add("Virgil");

		quotes.add("A man should be upright,\n not be kept upright.");
		authors.add("Marcus Aurelius");

		quotes.add("One man cannot practice many arts\n with success.");
		authors.add("Plato");

		quotes.add("Let us train our minds to desire\n what the situation demands.");
		authors.add("Seneca");

		quotes.add("The hottest love has the coldest end.");
		authors.add("Socrates");

		quotes.add("Justice… is a kind of compact not to\n harm or be harmed.");
		authors.add("Epicurus");

		quotes.add("The greatest remedy for anger is delay.");
		authors.add("Seneca");

		quotes.add("Dubium sapientiae initium.");
		authors.add("Descartes");

		quotes.add("Love conquers all.");
		authors.add("Virgil");

		quotes.add("Pleasure in the job puts perfection\n in the work.");
		authors.add("Aristotle");

		quotes.add("The meaning of peace is the\n absence of opposition to socialism.");
		authors.add("Karl Marx");

		quotes.add("The aim of the wise is not to secure\n pleasure, but to avoid pain.");
		authors.add("Aristotle");

		quotes.add("Veiling truth in mystery.");
		authors.add("Virgil");

		quotes.add("Enjoy yourself — it’s later than you think.");
		authors.add("Socrates");

		quotes.add("Perfect numbers like perfect men are very rare.");
		authors.add("Descartes");

		quotes.add("The act of dying is one of the acts of life.");
		authors.add("Marcus Aurelius");

		quotes.add("Education is the best provision for old age.");
		authors.add("Aristotle");

		quotes.add("An inner process stands in need of outward criteria.");
		authors.add("Wittgenstein");

		quotes.add("Not believing in force is the same\n as not believing in gravitation.");
		authors.add("Thomas Hobbes");

		quotes.add("The function of wisdom is to\n discriminate between good and evil.");
		authors.add("Cicero");

		quotes.add("Happy the man who has been able\n to learn the causes of things.");
		authors.add("Virgil");

		quotes.add("Well begun is half done.");
		authors.add("Aristotle");

		quotes.add("Be a philosopher but, amid all your\n philosophy be still a man.");
		authors.add("David Hume");

		quotes.add("Government has no other end,\n but the preservation of property.");
		authors.add("John Locke");

		quotes.add("Fortune sides with him who dares.");
		authors.add("Virgil");

		quotes.add("He has committed the crime who profits by it.");
		authors.add("Seneca");

		quotes.add("As long as you live,\n keep learning how to live.");
		authors.add("Seneca");

		quotes.add("The beginning of wisdom is a\n definition of terms.");
		authors.add("Socrates");

		quotes.add("Whatever you do, do with all your might.");
		authors.add("Cicero");

		quotes.add("The human being is not the lord of beings,\n but the shepherd of Being.");
		authors.add("Martin Heidegger");

		quotes.add("There is no harm in repeating a good thing.");
		authors.add("Plato");

		quotes.add("Time destroys the speculation of men,\n but it confirms nature.");
		authors.add("Cicero");

		quotes.add("Cogito, ergo sum.");
		authors.add("Descartes");

		quotes.add("I shudder when relating it.");
		authors.add("Virgil");

		quotes.add("Never injure a friend, even in jest.");
		authors.add("Cicero");

		quotes.add("Happiness depends upon ourselves.");
		authors.add("Aristotle");

		quotes.add("There is nothing on this earth more\n to be prized than true friendship.");
		authors.add("Saint Thomas Aquinas");

		quotes.add("Ability will never catch up with the\n demand for it.");
		authors.add("Confucius");

		quotes.add("Ex nihilo nihil fit.");
		authors.add("Descartes");

		quotes.add("No excellent soul is exempt from\n a mixture of madness.");
		authors.add("Aristotle");

		quotes.add("It is in the treatment of trifles\n that a person shows what they are.");
		authors.add("Schopenhauer");

		quotes.add("Where there is no property there is\n no injustice.");
		authors.add("John Locke");

		quotes.add("What can be shown, cannot be said.");
		authors.add("Wittgenstein");

		quotes.add("Man can alter his life by\n altering his thinking.");
		authors.add("William James");

		quotes.add("Take the course opposite to custom\n and you will almost always do well.");
		authors.add("Rousseau");

		quotes.add("The best interpreter of the law is custom.");
		authors.add("Cicero");

		quotes.add("Wealth is well known to be a great comforter.");
		authors.add("Plato");

		quotes.add("Success consecrates the most offensive crimes.");
		authors.add("Seneca");

		quotes.add("World history is a court of judgment.");
		authors.add("Hegel");

		quotes.add("It is a clear gain to sacrifice pleasure\n in order to avoid pain.");
		authors.add("Schopenhauer");

		quotes.add("Wherever you go, go with all your heart.");
		authors.add("Confucius");

		quotes.add("Nature does nothing in vain.");
		authors.add("Aristotle");

		quotes.add("The wisest have the most authority.");
		authors.add("Plato");

		quotes.add("As people are walking all the time,\n in the same spot, a path appears.");
		authors.add("John Locke");

		quotes.add("He enters the port with a full sail.");
		authors.add("Virgil");

		quotes.add("It is better, of course, to know useless things\n than to know nothing.");
		authors.add("Seneca");

		quotes.add("From the deepest desires often come\n the deadliest hate.");
		authors.add("Socrates");

		quotes.add("In the state of nature profit is the\n measure of right.");
		authors.add("Thomas Hobbes");

		quotes.add("True time is four-dimensional.");
		authors.add("Martin Heidegger");

		quotes.add("To some extent I liken slavery to death.");
		authors.add("Cicero");

		quotes.add("Slanderers do not hurt me because they do not hit me.");
		authors.add("Socrates");

		quotes.add("You get tragedy where the tree,\n instead of bending, breaks.");
		authors.add("Wittgenstein");

		quotes.add("The less we deserve good fortune,\n the more we hope for it.");
		authors.add("Seneca");

		quotes.add("An unexamined life is not worth living.");
		authors.add("Socrates");

		quotes.add("Death may be the greatest of\n all human blessings.");
		authors.add("Socrates");

		quotes.add("A confession has to be part of your new life.");
		authors.add("Wittgenstein");

		quotes.add("Some laws of state aimed at curbing crime\n are even more criminal.");
		authors.add("Friedrich Engels");

		quotes.add("Life is really simple, \nbut we insist on making it complicated.");
		authors.add("Confucius");

		quotes.add("The young are permanently in a state\n resembling intoxication.");
		authors.add("Aristotle");

		quotes.add("A kingdom founded on injustice never lasts.");
		authors.add("Seneca");

		quotes.add("And what is the greatest number?\n Number one.");
		authors.add("David Hume");

		quotes.add("If you want the present to be different\n from the past, study the past.");
		authors.add("Spinoza");

		quotes.add("There is no possible source of evil except good.");
		authors.add("Saint Augustine");

		quotes.add("Death, like birth, is a secret of Nature.");
		authors.add("Marcus Aurelius");

		quotes.add("I am a law only for my kind,\n I am no law for all.");
		authors.add("Nietzsche");

		quotes.add("Orators are most vehement when their cause is weak.");
		authors.add("Cicero");

		quotes.add("Fear is pain arising from the\n anticipation of evil.");
		authors.add("Aristotle");

		quotes.add("Where the willingness is great,\n the difficulties cannot be great.");
		authors.add("Machiavelli");

		quotes.add("Only the dead have seen the end of the war.");
		authors.add("Plato");

		quotes.add("In all things of nature there is\n something of the marvelous.");
		authors.add("Aristotle");

		quotes.add("Any man is liable to err,\n only a fool persists in error.");
		authors.add("Cicero");

		quotes.add("It is better to be feared than loved,\n if you cannot be both.");
		authors.add("Machiavelli");

		quotes.add("The less men think, the more they talk.");
		authors.add("Montesquieu");

		quotes.add("That which is not good for the bee-hive\n cannot be good for the bees.");
		authors.add("Marcus Aurelius");

		quotes.add("The severity of the laws prevents their execution.");
		authors.add("Montesquieu");

		quotes.add("Boredom is just the reverse side of fascination.");
		authors.add("Schopenhauer");

		quotes.add("If you are lonely when you’re alone,\n you are in bad company.");
		authors.add("Sartre");

		quotes.add("Like all dreamers,\nI mistook disenchantment for truth.");
		authors.add("Sartre");

		quotes.add("Do not weep; do not wax indignant.\n Understand.");
		authors.add("Spinoza");

		quotes.add("Cunning… is but the low mimic of wisdom.");
		authors.add("Plato");

		quotes.add("We do not know what is really good\n or bad fortune.");
		authors.add("Rousseau");

		quotes.add("No sane man will dance.");
		authors.add("Cicero");

		quotes.add("Will minus intellect constitutes vulgarity.");
		authors.add("Schopenhauer");

		quotes.add("We are doomed to cling to a life\n even while we find it unendurable.");
		authors.add("William James");

		quotes.add("Where fear is, happiness is not.");
		authors.add("Seneca");

		quotes.add("Nothing is to be preferred before justice.");
		authors.add("Socrates");

		quotes.add("An oppressive government is more\n to be feared than a tiger.");
		authors.add("Confucius");

		quotes.add("In order to improve the mind,\n we ought less learn than to contemplate.");
		authors.add("Descartes");

		quotes.add("Every man prefers belief to\n the exercise of judgment.");
		authors.add("Seneca");

		quotes.add("Before beginning, plan carefully.");
		authors.add("Cicero");

		quotes.add("The word of man is the most durable of all material.");
		authors.add("Schopenhauer");

		quotes.add("The face is the soul of the body.");
		authors.add("Wittgenstein");

		quotes.add("With people of limited ability\n modesty is merely honesty.");
		authors.add("Schopenhauer");

		quotes.add("To forget one’s purpose is the\n commonest form of stupidity.");
		authors.add("Nietzsche");

		quotes.add("Who can exhaust a man? \nWho knows a man’s resources?");
		authors.add("Sartre");

		quotes.add("Difficulties are things that show a person what they are.");
		authors.add("Epictetus");

		quotes.add("Frivolity is inborn, conceit acquired \nby education.");
		authors.add("Cicero");

		quotes.add("Plato is dear to me, but dearer\b still is truth.");
		authors.add("Aristotle");

		quotes.add("Tardiness often robs us opportunity,\n and the dispatch of our forces.");
		authors.add("Machiavelli");

		quotes.add("It is unbecoming for young men to utter maxims.");
		authors.add("Aristotle");

		quotes.add("Language is a part of our organism \nand no less complicated than it.");
		authors.add("Wittgenstein");

		quotes.add("Who can blind lover’s eyes?");
		authors.add("Virgil");

		quotes.add("As the twig is bent the tree inclines.");
		authors.add("Virgil");

		quotes.add("Tyranny naturally arises out of democracy.");
		authors.add("Plato");

		quotes.add("Age steals away all things, even the mind.");
		authors.add("Virgil");

		quotes.add("To convert somebody go and take them \nby the hand and guide them.");
		authors.add("Saint Thomas Aquinas");

		quotes.add("Religion is the opium of the masses.");
		authors.add("Karl Marx");

		quotes.add("Thrift is of great revenue.");
		authors.add("Cicero");

		quotes.add("The art of living is more like wrestling than dancing.");
		authors.add("Marcus Aurelius");

		quotes.add("Real knowledge is to know\n the extent of one’s ignorance.");
		authors.add("Confucius");

		quotes.add("Time is flying never to return.");
		authors.add("Virgil");

		quotes.add("Change in all things is sweet.");
		authors.add("Aristotle");

		quotes.add("The logic of the world is prior\n to all truth and falsehood.");
		authors.add("Wittgenstein");

		quotes.add("Friendship is a single soul dwelling in two bodies.");
		authors.add("Aristotle");

		quotes.add("Hatred is gained as much by good works as by evil.");
		authors.add("Machiavelli");

		quotes.add("Never was anything great achieved without danger.");
		authors.add("Machiavelli");

		quotes.add("The beginning is the most important part of the work.");
		authors.add("Plato");

		quotes.add("Poverty is the mother of crime.");
		authors.add("Marcus Aurelius");

		quotes.add("A constitution is the arrangement of \nmagistracies in a state.");
		authors.add("Aristotle");

		quotes.add("Money is human happiness in the\n abstract.");
		authors.add("Schopenhauer");

		quotes.add("Fear is proof of a degenerate mind.");
		authors.add("Virgil");

		quotes.add("If a man neglects education,\n he walks lame to the end of his life.");
		authors.add("Plato");

		quotes.add("The greatest virtues are those which are\n most useful to other persons.");
		authors.add("Aristotle");

		quotes.add("If we don’t know life, \nhow can we know death?");
		authors.add("Confucius");

		quotes.add("Leisure is the Mother of Philosophy.");
		authors.add("Thomas Hobbes");

		quotes.add("The art of living well and the\n art of dying well are one.");
		authors.add("Epicurus");

		quotes.add("Transcendence constitutes selfhood.");
		authors.add("Martin Heidegger");

		quotes.add("Each day provides its own gifts.");
		authors.add("Marcus Aurelius");

		quotes.add("First learn the meaning of what you say,\n and then speak.");
		authors.add("Epictetus");

		quotes.add("Honor is the reward of virtue.");
		authors.add("Cicero");

		quotes.add("The endeavor to understand is the first\n and only basis of virtue.");
		authors.add("Spinoza");

		quotes.add("The corruption of the best things gives\n rise to the worst.");
		authors.add("David Hume");

		quotes.add("What is done out of love always takes place \nbeyond good and evil.");
		authors.add("Nietzsche");

		quotes.add("Memory is the treasury and guardian of all things.");
		authors.add("Cicero");

		quotes.add("Amid the pressure of great events,\n a general principle gives no help.");
		authors.add("Hegel");

		quotes.add("Come what may, all bad fortune is to be\n conquered by endurance.");
		authors.add("Virgil");

		quotes.add("The human body is the best picture of the\n human soul.");
		authors.add("Wittgenstein");

		quotes.add("It never troubles the wolf how many the sheep may be.");
		authors.add("Virgil");

		quotes.add("Go forth a conqueror and win great victories.");
		authors.add("Virgil");

		quotes.add("The good is the beautiful.");
		authors.add("Plato");

		quotes.add("The good of the people is the greatest law.");
		authors.add("Cicero");

		quotes.add("To know what is right and not to do it\n is the worst cowardice.");
		authors.add("Confucius");

		quotes.add("Our affections as well as our bodies\n are in perpetual flux.");
		authors.add("Rousseau");

		quotes.add("The rich will do anything for the poor\n but get off their backs.");
		authors.add("Karl Marx");

		quotes.add("A happy life is one which is in accordance\n with its own nature.");
		authors.add("Seneca");

		quotes.add("Man is a wingless animal with two feet\n and flat nails.");
		authors.add("Plato");

		quotes.add("Fear is not a lasting teacher of duty.");
		authors.add("Cicero");

		quotes.add("They are able because they think they are able.");
		authors.add("Virgil");

		quotes.add("Will is to grace as the horse is to the rider.");
		authors.add("Saint Augustine");

		quotes.add("If it is not right do not do it;\n if it is not true do not say it.");
		authors.add("Marcus Aurelius");

		quotes.add("To go beyond is as wrong as to fall short.");
		authors.add("Confucius");

		quotes.add("Beauty is a short-lived tyranny.");
		authors.add("Socrates");

		quotes.add("The secret of all victory lies\n in the organization of the non-obvious.");
		authors.add("Marcus Aurelius");

		quotes.add("Old age: the crown of life, \nour play’s last act.");
		authors.add("Cicero");

		quotes.add("The discipline of desire is the\n background of character.");
		authors.add("John Locke");

		quotes.add("It is the quality rather than the quantity that matters.");
		authors.add("Seneca");

		quotes.add("Crime when it succeeds is called virtue.");
		authors.add("Seneca");

		quotes.add("The firm, the enduring, the simple,\n and the modest are near to virtue.");
		authors.add("Confucius");

		quotes.add("Their rage supplies them with weapons.");
		authors.add("Virgil");

		quotes.add("To seek the highest good is to live well.");
		authors.add("Saint Augustine");

		quotes.add("Politeness is to human nature what warmth is to wax.");
		authors.add("Schopenhauer");

		quotes.add("There must always remain something that is\n antagonistic to good.");
		authors.add("Plato");

		quotes.add("People do not understand what a\n great revenue economy is.");
		authors.add("Cicero");

		quotes.add("Every nation ridicules other nations,\n and all are right.");
		authors.add("Schopenhauer");

		quotes.add("Nature abhors annihilation.");
		authors.add("Cicero");

		quotes.add("A tear dries quickly when it is\n shed for troubles of others.");
		authors.add("Cicero");

		quotes.add("Is life worth living?\n It all depends on the liver.");
		authors.add("William James");

		quotes.add("We should weep for men at their birth,\n not at their death.");
		authors.add("Montesquieu");

		quotes.add("I have no need for good souls:\n an accomplice is what I wanted.");
		authors.add("Sartre");

		quotes.add("If you desire to be good,\n begin by believing that you are wicked.");
		authors.add("Epictetus");

		quotes.add("Quality is not an act, it is a habit.");
		authors.add("Aristotle");

		quotes.add("Justice is the set and constant purpose\n which gives every man his due.");
		authors.add("Cicero");

		quotes.add("What orators lack in depth\n they make up for in length.");
		authors.add("Montesquieu");

		quotes.add("Death is not the worst that can happen to men.");
		authors.add("Plato");

		quotes.add("If you care enough for a result,\n you will most certainly attain it.");
		authors.add("William James");

		quotes.add("Love is the attempt to form a friendship\n inspired by beauty.");
		authors.add("Cicero");

		quotes.add("We can’t all do everything.");
		authors.add("Virgil");

		quotes.add("Be kind, for everyone you meet \nis fighting a hard battle.");
		authors.add("Plato");

		quotes.add("Fashion for the most part is nothing but\n the ostentation of riches.");
		authors.add("John Locke");

		quotes.add("None but himself can be his parallel.");
		authors.add("Virgil");

		quotes.add("If you wish to be a writer, write.");
		authors.add("Epictetus");

		quotes.add("Brevity is a great charm of eloquence.");
		authors.add("Cicero");

		quotes.add("If we did not have rational souls,\n we would not be able to believe.");
		authors.add("Saint Augustine");

		quotes.add("We should feel sorrow,\n but not sink under its oppression.");
		authors.add("Confucius");

		quotes.add("Man is fully responsible for\n his nature and his choices.");
		authors.add("Sartre");

		quotes.add("Time passes irrevocably.");
		authors.add("Virgil");

		quotes.add("When the mind is thinking\n it is talking to itself.");
		authors.add("Plato");

		quotes.add("Trust not to much to appearances.");
		authors.add("Virgil");

		quotes.add("Any man may easily do harm,\n but not every man can do good to another.");
		authors.add("Plato");

		quotes.add("Complete abstinence is easier than perfect moderation.");
		authors.add("Saint Augustine");

		quotes.add("Once made equal to man,\n woman becomes his superior.");
		authors.add("Socrates");

		quotes.add("The one exclusive sign of thorough\n knowledge is the power of teaching.");
		authors.add("Aristotle");

		quotes.add("One crime has to be concealed by another.");
		authors.add("Seneca");

		quotes.add("Life begins on the other side of despair.");
		authors.add("Sartre");

		quotes.add("The world is independent of my will.");
		authors.add("Wittgenstein");

		quotes.add("He who hath many friends hath none.");
		authors.add("Aristotle");

		quotes.add("If a man knows not what harbor he seeks,\n any wind is the right wind.");
		authors.add("Seneca");

		quotes.add("Words are more treacherous\n and powerful than we think.");
		authors.add("Sartre");

		quotes.add("The confession of evil works is the\n first beginning of good works.");
		authors.add("Saint Augustine");

		quotes.add("There is no great genius without some touch of madness.");
		authors.add("Seneca");

		quotes.add("Do not laugh much or often or unrestrainedly.");
		authors.add("Epictetus");

		quotes.add("What you do not want done to yourself,\n do not do to others.");
		authors.add("Confucius");

		quotes.add("If particulars are to have meaning,\n there must be universals.");
		authors.add("Plato");

		quotes.add("Despise not death, but welcome it,\n for nature wills it like all else.");
		authors.add("Marcus Aurelius");

		quotes.add("The universe is transformation;\n our life is what our thoughts make it.");
		authors.add("Marcus Aurelius");

		quotes.add("Lunch kills half of Paris,\n supper the other half.");
		authors.add("Montesquieu");

		quotes.add("Anger cannot be dishonest.");
		authors.add("Marcus Aurelius");

		quotes.add("Everything in the world is purchased by labor.");
		authors.add("David Hume");

		quotes.add("Not cohabitation but consensus constitutes marriage.");
		authors.add("Cicero");

		quotes.add("If one swain scorns you, \nyou will soon find another.");
		authors.add("Virgil");

		quotes.add("A home without books is a body\n without soul.");
		authors.add("Cicero");

		quotes.add("If you have a garden and a library,\n you have everything you need.");
		authors.add("Cicero");

		quotes.add("Bad men are full of repentance.");
		authors.add("Aristotle");

		quotes.add("The heart is great which shows moderation\n in the midst of prosperity.");
		authors.add("Seneca");

		quotes.add("Patience is the companion of wisdom.");
		authors.add("Saint Augustine");

		quotes.add("Not by wrath does one kill,\n but by laughter.");
		authors.add("Nietzsche");

		quotes.add("They must often change,\n who would be constant in happiness or wisdom.");
		authors.add("Confucius");

		quotes.add("Making itself intelligible is suicide\n for philosophy.");
		authors.add("Martin Heidegger");

		quotes.add("No evil can happen to a good man,\n either in life or after death.");
		authors.add("Plato");

		quotes.add("I have always said and felt that\n true enjoyment can not be described.");
		authors.add("Rousseau");

		quotes.add("Existence precedes and rules essence.");
		authors.add("Sartre");

		quotes.add("All art is but imitation of nature.");
		authors.add("Seneca");

		quotes.add("The secret to humor is surprise.");
		authors.add("Aristotle");

		quotes.add("To live alone is the fate of all great souls.");
		authors.add("Schopenhauer");

		quotes.add("And remember, no matter where you go,\n there you are.");
		authors.add("Confucius");

		quotes.add("If we are not ashamed to think it,\n we should not be ashamed to say it.");
		authors.add("Cicero");

		quotes.add("Every guilty person is his own hangman.");
		authors.add("Seneca");

		quotes.add("Men should be bewailed at their birth,\n and not at their death.");
		authors.add("Montesquieu");

		quotes.add("Freedom is the right to live as we wish.");
		authors.add("Epictetus");

		quotes.add("The possible ranks higher than the actual.");
		authors.add("Martin Heidegger");

		quotes.add("All of my misfortunes come from \nhaving thought too well of my fellows.");
		authors.add("Rousseau");

		quotes.add("Charity is no substitute for justice withheld.");
		authors.add("Saint Augustine");

		quotes.add("Never give a sword to a man who\n can’t dance.");
		authors.add("Confucius");

		quotes.add("Omnia apud me mathematica fiunt.");
		authors.add("Descartes");

		quotes.add("Fame is the perfume of heroic deeds.");
		authors.add("Socrates");

		quotes.add("Fame is but the breath of people,\n and that often unwholesome.");
		authors.add("Rousseau");

		quotes.add("In strife who inquires whether\n stratagem or courage was used?");
		authors.add("Virgil");

		quotes.add("Treat a work of art like a prince.\n Let it speak to you first.");
		authors.add("Schopenhauer");

		quotes.add("You learn to know a pilot in a storm.");
		authors.add("Seneca");

		quotes.add("The essence of genius is to know what to overlook.");
		authors.add("William James");

		quotes.add("Persevere and preserve yourselves for better circumstances.");
		authors.add("Virgil");

		quotes.add("To refrain from imitation is the best revenge.");
		authors.add("Marcus Aurelius");

		quotes.add("Peace is a natural effect of trade.");
		authors.add("Montesquieu");

		quotes.add("Man is by nature a political animal.");
		authors.add("Aristotle");

		quotes.add("Philosophy begins in wonder.");
		authors.add("Plato");

		quotes.add("He like a rock in the sea unshaken\n stands his ground.");
		authors.add("Virgil");

		quotes.add("Nothing is so strongly fortified\n that it cannot be taken by money.");
		authors.add("Cicero");

		quotes.add("He who has great power should use it lightly.");
		authors.add("Seneca");

		quotes.add("Love takes up where knowledge leaves off.");
		authors.add("Saint Thomas Aquinas");

		quotes.add("How many are the things I can do without!");
		authors.add("Socrates");

		quotes.add("The soul becomes dyed with the color of\n its thoughts.");
		authors.add("Marcus Aurelius");

		quotes.add("Trust one who has tried.");
		authors.add("Virgil");

		quotes.add("The first and greatest victory is to conquer yourself.");
		authors.add("Plato");

		quotes.add("Freedom is what you do with what’s been done to you.");
		authors.add("Sartre");

		quotes.add("Reason has always existed,\n but not always in a reasonable form.");
		authors.add("Karl Marx");

		quotes.add("Poverty wants some, luxury many,\n and avarice all things.");
		authors.add("Seneca");

		quotes.add("Men exist for the sake of one another.");
		authors.add("Marcus Aurelius");

		quotes.add("What it lies in our power to do,\n it lies in our power not to do.");
		authors.add("Aristotle");

		quotes.add("Cannot people realize how large an\n income is thrift?");
		authors.add("Cicero");

		quotes.add("Contentment is natural wealth,\n luxury is artificial poverty.");
		authors.add("Socrates");

		quotes.add("Happiness is unrepentant pleasure.");
		authors.add("Socrates");

		quotes.add("Youth is easily deceived because\n it is quick to hope.");
		authors.add("Aristotle");

		quotes.add("Useless laws weaken the necessary laws.");
		authors.add("Montesquieu");

		quotes.add("They can conquer who believe they can.");
		authors.add("Virgil");

		quotes.add("Difficulties strengthen the mind,\n as labor does the body.");
		authors.add("Seneca");

		quotes.add("Your descendants shall gather your fruits.");
		authors.add("Virgil");

		quotes.add("A person’s fears are lighter\n when the danger is at hand.");
		authors.add("Seneca");

		quotes.add("No man’s knowledge here can go\n beyond his experience.");
		authors.add("John Locke");

		quotes.add("Honor has not to be won; \nit must only not be lost.");
		authors.add("Schopenhauer");

		quotes.add("Any man can make mistakes,\n but only an idiot persists in his error.");
		authors.add("Cicero");

		quotes.add("Let him that would move the world,\n first move himself.");
		authors.add("Socrates");

		quotes.add("If you wished to be loved, love.");
		authors.add("Seneca");

		quotes.add("Curiosity is the lust of the mind.");
		authors.add("Thomas Hobbes");


		quotes.add("To live is to think.");
		authors.add("Cicero");

		quotes.add("Confidence cannot find a place wherein \nto rest in safety.");
		authors.add("Virgil");

		quotes.add("Life, if well lived, is long enough.");
		authors.add("Seneca");

		quotes.add("Love, and do what you like.");
		authors.add("Saint Augustine");

		quotes.add("No great thing is created suddenly.");
		authors.add("Epictetus");

		quotes.add("Laws are silent in time of war.");
		authors.add("Cicero");

		quotes.add("To be conquered by yourself\n is of all things most shameful and vile.");
		authors.add("Plato");

		quotes.add("Hug the shore; let others try the deep.");
		authors.add("Virgil");

		quotes.add("Ambition is the immoderate desire for power.");
		authors.add("Spinoza");

		quotes.add("Je pense, donc je suis.");
		authors.add("Descartes");

		quotes.add("The higher we are placed,\n the more humbly we should walk.");
		authors.add("Cicero");

		quotes.add("The approach of liberty makes even an \nold man brave.");
		authors.add("Seneca");

		quotes.add("In business for yourself, not by yourself.");
		authors.add("William James");

		quotes.add("Hatred is an affair of the heart;\n contempt that of the head.");
		authors.add("Schopenhauer");

		quotes.add("One man excels in eloquence, another in arms.");
		authors.add("Virgil");

		quotes.add("Constant exposure to dangers will\n breed contempt for them.");
		authors.add("Seneca");

		quotes.add("All things deteriorate in time.");
		authors.add("Virgil");

		quotes.add("Reject your sense of injury \nand the injury itself disappears.");
		authors.add("Marcus Aurelius");

		quotes.add("Myself acquainted with misfortune,\n I learn to help the unfortunate.");
		authors.add("Virgil");

		quotes.add("For greed all nature is too little.");
		authors.add("Seneca");

		quotes.add("Someone who knows too much finds it hard not to lie.");
		authors.add("Wittgenstein");

		quotes.add("One change always leaves the way open for the establishment of others.");
		authors.add("Machiavelli");

		quotes.add("No price is too high to pay for\n the privilege of owning yourself.");
		authors.add("Nietzsche");

		quotes.add("One cannot become a saint when\n one works sixteen hours a day.");
		authors.add("Sartre");

		quotes.add("All paid jobs absorb and degrade\n the mind.");
		authors.add("Aristotle");

		quotes.add("The whole is more than the sum of its parts.");
		authors.add("Aristotle");

		quotes.add("Liberty is the right to do what the law permits.");
		authors.add("Montesquieu");

		quotes.add("Nothing in the affairs of men is worthy\n of great anxiety.");
		authors.add("Plato");

		quotes.add("I criticize by creation – not by finding fault.");
		authors.add("Cicero");

		quotes.add("You must live for another\n if you wish to live for yourself.");
		authors.add("Seneca");

		quotes.add("The spirit of moderation should also\n be the spirit of the lawgiver.");
		authors.add("Montesquieu");

		quotes.add("The visionary lies to himself,\n the liar only to others.");
		authors.add("Nietzsche");

		quotes.add("When anger rises, think of the consequences.");
		authors.add("Confucius");

		quotes.add("Skillful pilots gain their reputation\n from storms and tempest.");
		authors.add("Epicurus");

		quotes.add("Mere goodness can achieve little\n against the power of nature.");
		authors.add("Hegel");

		quotes.add("Ignorance, the root and stem of all evil.");
		authors.add("Plato");

		quotes.add("Fortitude is the guard and support\n of the other virtues.");
		authors.add("John Locke");

		quotes.add("Love is composed of a single soul\n inhabiting two bodies.");
		authors.add("Aristotle");

		quotes.add("There is no hope unmingled with fear,\n and no fear unmingled with hope.");
		authors.add("Spinoza");

		quotes.add("Wisdom is learning what to overlook.");
		authors.add("William James");

		quotes.add("At the touch of a lover, everyone becomes a poet.");
		authors.add("Plato");

		quotes.add("Nobody can give you wiser advice than yourself.");
		authors.add("Cicero");

		quotes.add("Beware of the person of one book.");
		authors.add("Saint Thomas Aquinas");

		quotes.add("No law or ordinance is mightier than understanding.");
		authors.add("Plato");

		quotes.add("Even if you have nothing to write,\n write and say so.");
		authors.add("Cicero");

		quotes.add("Fortune favours the bold.");
		authors.add("Virgil");

		quotes.add("Every calamity is to be overcome by\n endurance.");
		authors.add("Virgil");

		quotes.add("A fault is fostered by concealment.");
		authors.add("Virgil");

		quotes.add("There’s a snake lurking in the grass.");
		authors.add("Virgil");

		quotes.add("No untroubled day has ever dawned for me.");
		authors.add("Seneca");

		quotes.add("I am not a Marxist.");
		authors.add("Karl Marx");

		quotes.add("Control thy passions lest they take \nvengence on thee.");
		authors.add("Epictetus");

		quotes.add("Character may almost be called the most\n effective means of persuasion.");
		authors.add("Aristotle");

		quotes.add("Music is the melody whose text is the world.");
		authors.add("Schopenhauer");

		quotes.add("The law is reason, free from passion.");
		authors.add("Aristotle");

		quotes.add("It seems that laughter needs an echo.");
		authors.add("Henri Bergson");

		quotes.add("Hope is a waking dream.");
		authors.add("Aristotle");

		quotes.add("Compared to what we ought to be,\n we are half awake.");
		authors.add("William James");

		quotes.add("Nothing is divine but what is agreeable to reason.");
		authors.add("Kant");

		quotes.add("Time flies never to be recalled.");
		authors.add("Virgil");

		quotes.add("Those who wish to sing always\n find a song.");
		authors.add("Plato");

		quotes.add("I am about to take my last voyage,\n a great leap in the dark.");
		authors.add("Thomas Hobbes");

		quotes.add("To love to read is to exchange hours of\n ennui for hours of delight.");
		authors.add("Montesquieu");

		quotes.add("Be careful when you fight the monsters,\n lest you become one.");
		authors.add("Nietzsche");

		quotes.add("The motive power of democracy is love");
		authors.add("Henri Bergson");

		quotes.add("In war there is no prize for runner-up.");
		authors.add("Seneca");

		quotes.add("A good decision is based on knowledge\n and not on numbers.");
		authors.add("Plato");

		quotes.add("Bashfulness is an ornament to youth,\n but a reproach to old age.");
		authors.add("Aristotle");

		quotes.add("Courage is a mean with regard to\n fear and confidence.");
		authors.add("Aristotle");

		quotes.add("He who is not a good servant will not be\n a good master.");
		authors.add("Plato");

		quotes.add("One must steer, not talk.");
		authors.add("Seneca");

		quotes.add("The eye sees only what the mind is prepared\n to comprehend.");
		authors.add("Henri Bergson");

		quotes.add("Where a man can live, he can also live well.");
		authors.add("Marcus Aurelius");

		quotes.add("The comic and the tragic lie inseparably close, \nlike light and shadow.");
		authors.add("Socrates");

		quotes.add("Execute every act of thy life as though\n it were thy last.");
		authors.add("Marcus Aurelius");

		quotes.add("The gods too are fond of a joke.");
		authors.add("Aristotle");

		quotes.add("To live happily is an inward power\n of the soul.");
		authors.add("Marcus Aurelius");

		quotes.add("Whatever deceives men seems to produce\n a magical enchantment.");
		authors.add("Plato");

		quotes.add("All cruelty springs from weakness.");
		authors.add("Seneca");

		quotes.add("In everything truth surpasses the\n imitation and copy.");
		authors.add("Cicero");

		quotes.add("Democracy passes into despotism.");
		authors.add("Plato");

		quotes.add("The limits of my language means the\n limits of my world.");
		authors.add("Wittgenstein");

		quotes.add("Perhaps even these things, one day,\n will be pleasing to remember.");
		authors.add("Virgil");

		quotes.add("Good habits formed at youth make all the difference.");
		authors.add("Aristotle");

		quotes.add("If you judge, investigate.");
		authors.add("Seneca");

		quotes.add("We tell lies, yet it is easy to show that\n lying is immoral.");
		authors.add("Epictetus");

		quotes.add("Thinking: the talking of the soul with itself.");
		authors.add("Plato");

		quotes.add("The energy of the mind is the essence of life.");
		authors.add("Aristotle");

		quotes.add("All that I know about my life, it seems,\n I have learned in books.");
		authors.add("Sartre");		
	}
	
	public String nextQuote() {
		Random r = new Random();
		index = r.nextInt(quotes.size());
		return quotes.get(index);
	}
	
	public String nextAuthor() {
		return authors.get(index);
	}
	
}
