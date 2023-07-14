clc;
clear;
close all;

%% Problem Info
nDim = 5;
goldNumber = nDim * (nDim ^ 2 + 1)/2;

FitenessFunction = @(x) CalculateFitness(x,nDim,goldNumber);

populationSize = 100;
GenereationNumber = 200;
parentNumber = populationSize / 2;

RecombinationRate = 0.9;
MutationRate = 0.2;

%% Initialization
individual.Position = [];
individual.Fitness = [];
Population = repmat(individual,populationSize,1);

for i=1:populationSize
    Population(i).Position = randperm(nDim^2);
    Population(i).Fitness = CalculateFitness(Population(i).Position,nDim,goldNumber);
end

%% Main Loop
tic;
for i=1:GenereationNumber
    parents = doParentSelection(Population,populationSize,parentNumber);
    offsprings = doRecombination(parents,nDim,goldNumber,RecombinationRate);
    offsprings = doMutation(offsprings,nDim,goldNumber,MutationRate);
    Population = doSurvivorSelection(Population,offsprings);
end
toc;

%% Plot Results
answer = reshape(Population(1).Position,[nDim nDim]);
disp(['Best Individual: ' mat2str(answer) ', with Fitness: ' num2str(Population(1).Fitness)]);








