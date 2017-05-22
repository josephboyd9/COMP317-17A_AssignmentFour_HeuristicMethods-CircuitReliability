# COMP317-17A_AssignmentFour_HeuristicMethods-CircuitReliability


COMP317-17A — Assignment Four
Heuristic methods — circuit reliability
Due: Saturday, 10th June 2017 — 11.00pm

Description: Implement either a simulated annealing or genetic algorithm solution to the circuit reliability problem outlined below. The solution must be written in Java (unless prior consent for another language is obtained from the marking tutor), compile and run on Linux machines in Lab 6 within R Block. This assignment must be done in pairs, so find a partner or ask me to help you find one.

Overview: Consider a serial multi-stage electronic circuit designed, say, as a safety system that triggers shutdown if a problem is detected. Each stage is responsible for one critical subsystem, such that if any stage detects a problem then the shutdown ensues.

Each stage has an electronic device with a certain reliability rating. For example, a device with reliability 0.85 has a (1-0.85)=0.15 probability of failure. The overall reliability of the stage can be improved by adding parallel redundant devices of the same type, such that the entire stage fails only if all its redundant devices fail, the probability of which is the product of the probability of failure of each individual device. That is, if device di has reliability ri and stage Si has mi > 0 redundant devices of this type, then the reliability of the entire stage is 1-(1-ri)mi.

If any stage fails then the entire system fails; thus, the reliability of the entire safety circuit is the product of the reliability of all its stages.

Each device di has a cost ci. The problem we need to solve is figuring out how many of each device type we should have at each stage to maximize overall circuit reliability for a budget of Cmax.

More plainly, find a set of integers M={m1,m2,m3 ... mn} for an n-stage circuit where mi > 0 specifies how many devices of type di with reliability ri and cost ci are needed in stage Si to maximize reliability at an overall cost C <= Cmax.

Specification: Call your program object Solve.java invoked from the command line as follows:
% java Solve filename.txt budget limit
where filename.txt is the name of a plain text file whose i-th line gives ri and ci of di as two floating point values separated by a space. budget is the maximum amount of money to be spent on the final circuit, and limit is the maximum number of candidate solutions that can be evaluated in the search for a solution.

Output: Once your program hits its limit it must display one line of output for each stage showing the following five things: reliability on one device and the cost of one device (copied from file input) followed by the qty of this device to be included in this stage, the overall reliability of this stage and finally the total cost of this stage. After this table has been display, your program should add one more line giving the overall reliability of this configuration and the total final cost.
Submission requirements:
Submit a copy of your well-documented source code using moodle following the procedure of previous assignments. Do not include data files or compiled class files—just the source code, with an optional README text file if you want to say anything about your submission. (Only one member of the partnership should submit the solution.) Marking will be based mainly on the quality of the code, but also on how good a solution your program tends to be able to produce in comparison to solutions from other students using the same general heuristic method. Some sample data files will be posted on Moodle so you may discuss results with other partnerships.

Tony C. Smith, 22/05/2017
